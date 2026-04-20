package com.yourcompany.platform.common.logging.model;

/*
 * @author josec
 * @project yourcompany-platform
 */public record CorrelationContext(
        String correlationId,
        String traceId
) {
}
