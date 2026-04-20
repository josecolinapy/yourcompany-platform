package com.yourcompany.platform.common.logging.support;

import com.yourcompany.platform.common.logging.constants.LoggingConstants;
import com.yourcompany.platform.common.logging.model.CorrelationContext;

import org.slf4j.MDC;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class MdcSupport {
    private MdcSupport() {
    }

    public static void putCorrelationContext(final CorrelationContext context) {
        if (context == null) {
            return;
        }
        putIfPresent(LoggingConstants.CORRELATION_ID, context.correlationId());
        putIfPresent(LoggingConstants.TRACE_ID, context.traceId());
    }

    public static void put(final String key, final String value) {
        putIfPresent(key, value);
    }

    public static void clear() {
        MDC.clear();
    }

    private static void putIfPresent(final String key, final String value) {
        if (value != null && !value.isBlank()) {
            MDC.put(key, value);
        }
    }
}
