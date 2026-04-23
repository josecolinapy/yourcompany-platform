package com.yourcompany.platform.starter.aop.observability.aspect;

import com.yourcompany.platform.common.aop.annotation.Logged;
import com.yourcompany.platform.common.logging.constants.LoggingConstants;
import com.yourcompany.platform.starter.aop.observability.autoconfigure.PlatformAopProperties;
import com.yourcompany.platform.starter.aop.observability.model.EffectiveMethodLoggingSettings;
import com.yourcompany.platform.starter.aop.observability.support.*;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/*
 * @author josec
 * @project yourcompany-platform
 */
@Aspect
public class LoggedAspect {
    private final PlatformAopProperties properties;
    private final MethodLoggingSettingsResolver settingsResolver;
    private final PayloadLogSupport payloadLogSupport;

    public LoggedAspect(
            final PlatformAopProperties properties,
            final MethodLoggingSettingsResolver settingsResolver,
            final PayloadLogSupport payloadLogSupport
    ) {
        this.properties = properties;
        this.settingsResolver = settingsResolver;
        this.payloadLogSupport = payloadLogSupport;
    }

    @Around("@annotation(logged) || @within(logged)")
    public Object around(final ProceedingJoinPoint joinPoint, final Logged logged) throws Throwable {
        if (!properties.isEnabled() || !properties.getLogging().isEnabled() || !logged.enabled()) {
            return joinPoint.proceed();
        }

        final Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        final String methodKey = MethodSignatureSupport.methodKey(joinPoint);
        final String methodName = MethodSignatureSupport.methodName(joinPoint);
        final EffectiveMethodLoggingSettings settings = settingsResolver.resolve(methodKey);
        final long start = System.nanoTime();

        if (!settings.enabled()) {
            return joinPoint.proceed();
        }

        if (settings.logRequest()) {
            AspectLogSupport.log(
                    logger,
                    settings.level(),
                    "AOP request method={} correlationId={} payload={}",
                    settings.includeMethodSignature() ? methodKey : methodName,
                    MDC.get(LoggingConstants.CORRELATION_ID),
                    payloadLogSupport.serializeArguments(joinPoint)
            );
        }

        try {
            final Object result = joinPoint.proceed();

            if (settings.logResponse() && (result != null || settings.includeVoidResponse())) {
                AspectLogSupport.log(
                        logger,
                        settings.level(),
                        "AOP response method={} correlationId={} payload={}",
                        settings.includeMethodSignature() ? methodKey : methodName,
                        MDC.get(LoggingConstants.CORRELATION_ID),
                        result == null ? "<void>" : payloadLogSupport.serializeResult(result)
                );
            }

            if (settings.logExecutionTime()) {
                AspectLogSupport.log(
                        logger,
                        settings.level(),
                        "AOP execution method={} correlationId={} elapsedMs={}",
                        settings.includeMethodSignature() ? methodKey : methodName,
                        MDC.get(LoggingConstants.CORRELATION_ID),
                        ExecutionTimeSupport.elapsedMs(start)
                );
            }

            return result;
        } catch (final Throwable throwable) {
            if (settings.logException()) {
                logger.error(
                        "AOP exception method={} correlationId={} elapsedMs={} errorType={} message={}",
                        settings.includeMethodSignature() ? methodKey : methodName,
                        MDC.get(LoggingConstants.CORRELATION_ID),
                        ExecutionTimeSupport.elapsedMs(start),
                        throwable.getClass().getSimpleName(),
                        throwable.getMessage(),
                        throwable
                );
            }

            if (settings.logExecutionTime() && !settings.logException()) {
                AspectLogSupport.log(
                        logger,
                        settings.level(),
                        "AOP execution method={} correlationId={} elapsedMs={}",
                        settings.includeMethodSignature() ? methodKey : methodName,
                        MDC.get(LoggingConstants.CORRELATION_ID),
                        ExecutionTimeSupport.elapsedMs(start)
                );
            }

            throw throwable;
        }
    }
}
