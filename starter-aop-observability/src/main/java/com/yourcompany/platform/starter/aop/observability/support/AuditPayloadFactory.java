package com.yourcompany.platform.starter.aop.observability.support;

import com.yourcompany.platform.common.aop.annotation.Auditable;
import com.yourcompany.platform.common.aop.model.AuditResult;
import com.yourcompany.platform.common.logging.constants.LoggingConstants;
import com.yourcompany.platform.starter.aop.observability.publisher.AuditEvent;

import org.slf4j.MDC;

import java.time.Instant;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class AuditPayloadFactory {
    private AuditPayloadFactory() {
    }

    public static AuditEvent create(
            final Auditable auditable,
            final String method,
            final AuditResult result,
            final long durationMs,
            final String detail
    ) {
        return new AuditEvent(
                auditable.action(),
                auditable.resource(),
                method,
                result,
                MDC.get(LoggingConstants.CORRELATION_ID),
                durationMs,
                detail,
                Instant.now()
        );
    }
}
