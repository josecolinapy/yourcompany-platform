package com.yourcompany.platform.starter.aop.observability.aspect;

import com.yourcompany.platform.common.aop.annotation.Traced;
import com.yourcompany.platform.starter.aop.observability.autoconfigure.PlatformAopProperties;
import com.yourcompany.platform.starter.aop.observability.support.ExecutionTimeSupport;
import com.yourcompany.platform.starter.aop.observability.support.MethodSignatureSupport;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * @author josec
 * @project yourcompany-platform
 */
@Aspect
public class TracedAspect {
    private final PlatformAopProperties properties;

    public TracedAspect(final PlatformAopProperties properties) {
        this.properties = properties;
    }

    @Around("@annotation(traced)")
    public Object around(final ProceedingJoinPoint joinPoint, final Traced traced) throws Throwable {
        final Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        final String method = MethodSignatureSupport.methodName(joinPoint);
        final String operation = traced.value().isBlank() ? method : traced.value();
        final long start = System.nanoTime();

        logger.debug("Trace start operation={} method={}", operation, method);
        try {
            final Object result = joinPoint.proceed();
            if (properties.isTraceEnabled()) {
                logger.debug("Trace end operation={} method={} elapsedMs={}", operation, method, ExecutionTimeSupport.elapsedMs(start));
            }
            return result;
        } catch (final Throwable throwable) {
            logger.debug("Trace error operation={} method={} elapsedMs={} error={}", operation, method, ExecutionTimeSupport.elapsedMs(start), throwable.getClass().getSimpleName());
            throw throwable;
        }
    }
}
