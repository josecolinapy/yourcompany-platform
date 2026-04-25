package com.yourcompany.platform.common.core.text;

import java.util.Map;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class TemplateSupport {
    private TemplateSupport() {
    }

    public static String replacePlaceholders(final String template, final Map<String, Object> values) {
        if (template == null) {
            return null;
        }
        if (values == null || values.isEmpty()) {
            return template;
        }
        String result = template;
        for (final Map.Entry<String, Object> entry : values.entrySet()) {
            final String placeholder = "{" + entry.getKey() + "}";
            final String replacement = entry.getValue() == null ? "" : String.valueOf(entry.getValue());
            result = result.replace(placeholder, replacement);
        }
        return result;
    }
}
