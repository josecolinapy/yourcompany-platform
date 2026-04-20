package com.yourcompany.platform.common.web;

import java.time.Instant;

/*
 * @author josec
 * @project yourcompany-platform
 */public record ResponseMetadata(
        Instant timestamp,
        String correlationId,
        String traceId
) {
}
