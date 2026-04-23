package com.yourcompany.platform.starter.aop.observability.model;

import com.yourcompany.platform.common.aop.model.LogLevel;

/*
 * @author josec
 * @project yourcompany-platform
 */
public record EffectiveMethodLoggingSettings(
        boolean enabled,
        boolean logRequest,
        boolean logResponse,
        boolean logException,
        boolean logExecutionTime,
        boolean includeVoidResponse,
        boolean includeArgumentTypes,
        boolean includeMethodSignature,
        int maxPayloadLength,
        boolean maskSensitiveData,
        LogLevel level
) {
}
