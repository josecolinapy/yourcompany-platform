package com.yourcompany.platform.starter.aop.observability.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yourcompany.platform.common.aop.annotation.MaskField;
import com.yourcompany.platform.common.aop.annotation.SanitizedLog;
import com.yourcompany.platform.common.aop.model.MaskStrategy;
import com.yourcompany.platform.starter.aop.observability.autoconfigure.PlatformAopProperties;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
 * @author josec
 * @project yourcompany-platform
 */
public class PayloadLogSupport {
    private static final String EXCLUDED_TYPE = "<excluded-type>";
    private static final String UNSERIALIZABLE = "<unserializable>";
    private static final String MASKED = "***";

    private final ObjectMapper objectMapper;
    private final PlatformAopProperties properties;

    public PayloadLogSupport(final ObjectMapper objectMapper, final PlatformAopProperties properties) {
        this.objectMapper = objectMapper;
        this.properties = properties;
    }

    public String serializeArguments(final ProceedingJoinPoint joinPoint) {
        final Object[] args = joinPoint.getArgs();
        final List<Object> serialized = new ArrayList<>();
        for (final Object arg : args) {
            if (arg == null) {
                serialized.add(null);
                continue;
            }
            if (isExcludedType(arg.getClass().getName())) {
                serialized.add(EXCLUDED_TYPE + ":" + arg.getClass().getSimpleName());
                continue;
            }
            serialized.add(sanitizeValue(arg));
        }
        return truncate(toJson(serialized));
    }

    public String serializeResult(final Object result) {
        if (result == null) {
            return "null";
        }
        if (isExcludedType(result.getClass().getName())) {
            return EXCLUDED_TYPE + ":" + result.getClass().getSimpleName();
        }
        return truncate(toJson(sanitizeValue(result)));
    }

    private Object sanitizeValue(final Object value) {
        if (!properties.getLogging().isMaskSensitiveData() || value == null) {
            return value;
        }

        if (value instanceof Map<?, ?> map) {
            final Map<String, Object> sanitized = new LinkedHashMap<>();
            for (final Map.Entry<?, ?> entry : map.entrySet()) {
                final String key = String.valueOf(entry.getKey());
                sanitized.put(key, isSensitive(key) ? MASKED : sanitizeValue(entry.getValue()));
            }
            return sanitized;
        }

        if (value instanceof Iterable<?> iterable) {
            final List<Object> sanitized = new ArrayList<>();
            for (final Object item : iterable) {
                sanitized.add(sanitizeValue(item));
            }
            return sanitized;
        }

        if (value.getClass().isArray()) {
            final int length = Array.getLength(value);
            final List<Object> sanitized = new ArrayList<>(length);
            for (int i = 0; i < length; i++) {
                sanitized.add(sanitizeValue(Array.get(value, i)));
            }
            return sanitized;
        }

        if (isSimpleValue(value)) {
            return value;
        }

        return sanitizeBean(value);
    }

    private Object sanitizeBean(final Object value) {
        final Map<String, Object> sanitized = new LinkedHashMap<>();
        final boolean forceSanitize = value.getClass().isAnnotationPresent(SanitizedLog.class);

        for (final Field field : value.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                final Object fieldValue = field.get(value);
                final String fieldName = field.getName();
                final MaskField maskField = field.getAnnotation(MaskField.class);

                if (maskField != null) {
                    sanitized.put(fieldName, applyMask(maskField.strategy(), fieldValue));
                } else if (forceSanitize || isSensitive(fieldName)) {
                    sanitized.put(fieldName, MASKED);
                } else {
                    sanitized.put(fieldName, sanitizeValue(fieldValue));
                }
            } catch (final IllegalAccessException exception) {
                sanitized.put(field.getName(), UNSERIALIZABLE);
            }
        }

        return sanitized;
    }

    private Object applyMask(final MaskStrategy strategy, final Object fieldValue) {
        if (fieldValue == null) {
            return null;
        }
        final String value = String.valueOf(fieldValue);
        return switch (strategy) {
            case FULL -> MASKED;
            case LAST_4 -> value.length() <= 4 ? MASKED : "*".repeat(value.length() - 4) + value.substring(value.length() - 4);
            case EMAIL -> maskEmail(value);
        };
    }

    private String maskEmail(final String value) {
        final int atIndex = value.indexOf('@');
        if (atIndex <= 1) {
            return MASKED;
        }
        return value.charAt(0) + MASKED + value.substring(atIndex - 1);
    }

    private boolean isSimpleValue(final Object value) {
        return value instanceof String
                || value instanceof Number
                || value instanceof Boolean
                || value.getClass().isEnum();
    }

    private boolean isSensitive(final String fieldName) {
        return properties.getLogging().getSensitiveFieldNames().stream()
                .anyMatch(sensitive -> sensitive.equalsIgnoreCase(fieldName));
    }

    private boolean isExcludedType(final String typeName) {
        return properties.getLogging().getExcludedArgumentTypes().stream()
                .anyMatch(excluded -> excluded.equals(typeName));
    }

    private String toJson(final Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (final JsonProcessingException exception) {
            return UNSERIALIZABLE;
        }
    }

    private String truncate(final String value) {
        if (value == null) {
            return null;
        }
        final int max = properties.getLogging().getMaxPayloadLength();
        if (max <= 0 || value.length() <= max) {
            return value;
        }
        return value.substring(0, max) + "...<truncated>";
    }
}
