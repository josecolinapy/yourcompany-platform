package com.yourcompany.platform.starter.aop.observability.support;

import com.yourcompany.platform.starter.aop.observability.autoconfigure.PlatformAopProperties;
import com.yourcompany.platform.starter.aop.observability.model.EffectiveMethodLoggingSettings;
import com.yourcompany.platform.starter.aop.observability.model.MethodLoggingRule;

import org.springframework.util.PatternMatchUtils;

import java.util.Comparator;
import java.util.Optional;

/*
 * @author josec
 * @project yourcompany-platform
 */
public class MethodLoggingSettingsResolver {
    private final PlatformAopProperties properties;

    public MethodLoggingSettingsResolver(final PlatformAopProperties properties) {
        this.properties = properties;
    }

    public EffectiveMethodLoggingSettings resolve(final String methodKey) {
        final PlatformAopProperties.Logging logging = properties.getLogging();
        final MethodLoggingRule rule = resolveRule(methodKey).orElse(null);

        final boolean enabled = rule != null ? rule.isEnabled() : logging.isEnabled();

        return new EffectiveMethodLoggingSettings(
                enabled,
                resolveBoolean(rule == null ? null : rule.getLogRequest(), logging.isLogRequest()),
                resolveBoolean(rule == null ? null : rule.getLogResponse(), logging.isLogResponse()),
                resolveBoolean(rule == null ? null : rule.getLogException(), logging.isLogException()),
                resolveBoolean(rule == null ? null : rule.getLogExecutionTime(), logging.isLogExecutionTime()),
                logging.isIncludeVoidResponse(),
                logging.isIncludeArgumentTypes(),
                logging.isIncludeMethodSignature(),
                logging.getMaxPayloadLength(),
                logging.isMaskSensitiveData(),
                logging.getLevel()
        );
    }

    private Optional<MethodLoggingRule> resolveRule(final String methodKey) {
        return properties.getLogging().getRules().stream()
                .filter(rule -> rule.getPattern() != null && PatternMatchUtils.simpleMatch(rule.getPattern(), methodKey))
                .max(Comparator.comparingInt(rule -> specificity(rule.getPattern())));
    }

    private int specificity(final String pattern) {
        return pattern == null ? 0 : pattern.replace("*", "").length();
    }

    private boolean resolveBoolean(final Boolean overrideValue, final boolean defaultValue) {
        return overrideValue != null ? overrideValue : defaultValue;
    }
}
