package com.yourcompany.platform.starter.aop.observability.aspect;

import com.yourcompany.platform.common.aop.annotation.Auditable;
import com.yourcompany.platform.common.aop.model.AuditResult;
import com.yourcompany.platform.starter.aop.observability.autoconfigure.PlatformAopProperties;
import com.yourcompany.platform.starter.aop.observability.publisher.AuditEventPublisher;
import com.yourcompany.platform.starter.aop.observability.support.AuditPayloadFactory;
import com.yourcompany.platform.starter.aop.observability.support.ExecutionTimeSupport;
import com.yourcompany.platform.starter.aop.observability.support.MethodSignatureSupport;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/*
 * @author josec
 * @project yourcompany-platform
 */
@Aspect
public class AuditableAspect {
    private final PlatformAopProperties properties;
    private final AuditEventPublisher auditEventPublisher;

    public AuditableAspect(final PlatformAopProperties properties, final AuditEventPublisher auditEventPublisher) {
        this.properties = properties;
        this.auditEventPublisher = auditEventPublisher;
    }

    @Around("@annotation(auditable)")
    public Object around(final ProceedingJoinPoint joinPoint, final Auditable auditable) throws Throwable {
        final long start = System.nanoTime();
        final String method = MethodSignatureSupport.methodName(joinPoint);

        try {
            final Object result = joinPoint.proceed();
            if (properties.isAuditEnabled()) {
                auditEventPublisher.publish(
                        AuditPayloadFactory.create(
                                auditable,
                                method,
                                AuditResult.SUCCESS,
                                ExecutionTimeSupport.elapsedMs(start),
                                auditable.includeResult() && result != null ? result.getClass().getSimpleName() : null
                        )
                );
            }
            return result;
        } catch (final Throwable throwable) {
            if (properties.isAuditEnabled()) {
                auditEventPublisher.publish(
                        AuditPayloadFactory.create(
                                auditable,
                                method,
                                AuditResult.FAILURE,
                                ExecutionTimeSupport.elapsedMs(start),
                                throwable.getClass().getSimpleName()
                        )
                );
            }
            throw throwable;
        }
    }
}
