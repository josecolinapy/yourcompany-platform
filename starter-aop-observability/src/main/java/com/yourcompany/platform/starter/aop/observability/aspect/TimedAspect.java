package com.yourcompany.platform.starter.aop.observability.aspect;

import com.yourcompany.platform.common.aop.annotation.Timed;
import com.yourcompany.platform.common.aop.constants.AopConstants;
import com.yourcompany.platform.starter.aop.observability.autoconfigure.PlatformAopProperties;
import com.yourcompany.platform.starter.aop.observability.support.ExecutionTimeSupport;
import com.yourcompany.platform.starter.aop.observability.support.MethodSignatureSupport;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

/*
 * @author josec
 * @project yourcompany-platform
 */
@Aspect
public class TimedAspect {
    private final PlatformAopProperties properties;
    private final MeterRegistry meterRegistry;

    public TimedAspect(final PlatformAopProperties properties, final MeterRegistry meterRegistry) {
        this.properties = properties;
        this.meterRegistry = meterRegistry;
    }

    @Around("@annotation(timed)")
    public Object around(final ProceedingJoinPoint joinPoint, final Timed timed) throws Throwable {
        final Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        final String method = MethodSignatureSupport.methodName(joinPoint);
        final long start = System.nanoTime();

        try {
            return joinPoint.proceed();
        } finally {
            final long elapsedMs = ExecutionTimeSupport.elapsedMs(start);
            final String metricName = timed.value().isBlank() ? AopConstants.DEFAULT_METRIC_PREFIX + ".execution" : timed.value();

            if (timed.log()) {
                if (timed.warnThresholdMs() > -1 && elapsedMs >= timed.warnThresholdMs()) {
                    logger.warn("Timed method={} elapsedMs={} thresholdMs={}", method, elapsedMs, timed.warnThresholdMs());
                } else {
                    logger.info("Timed method={} elapsedMs={}", method, elapsedMs);
                }
            }

            if (properties.isMetricsEnabled() && timed.metric() && meterRegistry != null) {
                Timer.builder(metricName)
                        .tag("method", method)
                        .register(meterRegistry)
                        .record(Duration.ofMillis(elapsedMs));
            }
        }
    }
}
