package com.yourcompany.platform.common.core.text;

import java.util.regex.Pattern;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class RegexSupport {
    private RegexSupport() {
    }

    public static boolean matches(final String regex, final String value) {
        if (regex == null || value == null) {
            return false;
        }
        return Pattern.matches(regex, value);
    }

    public static String replaceAll(final String regex, final String replacement, final String value) {
        if (value == null) {
            return null;
        }
        return value.replaceAll(regex, replacement);
    }

    public static String removeAll(final String regex, final String value) {
        return replaceAll(regex, "", value);
    }
}
