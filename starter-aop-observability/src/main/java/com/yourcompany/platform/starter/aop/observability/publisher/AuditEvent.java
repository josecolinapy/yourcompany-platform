package com.yourcompany.platform.starter.aop.observability.publisher;

import com.yourcompany.platform.common.aop.model.AuditResult;

import java.time.Instant;

/*
 * @author josec
 * @project yourcompany-platform
 */public record AuditEvent(
        String action,
        String resource,
        String method,
        AuditResult result,
        String correlationId,
        long durationMs,
        String detail,
        Instant occurredAt
) {
}
