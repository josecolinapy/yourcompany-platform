package com.yourcompany.platform.common.core.text;

import java.util.Collection;
import java.util.Objects;
import java.util.StringJoiner;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class StringSupport {
    private StringSupport() {
    }

    public static boolean isBlank(final String value) {
        return value == null || value.isBlank();
    }

    public static boolean isNotBlank(final String value) {
        return !isBlank(value);
    }

    public static String trimToNull(final String value) {
        if (value == null) {
            return null;
        }
        final String trimmed = value.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }

    public static String trimToEmpty(final String value) {
        return value == null ? "" : value.trim();
    }

    public static String defaultIfBlank(final String value, final String defaultValue) {
        return isBlank(value) ? defaultValue : value;
    }

    public static String left(final String value, final int length) {
        if (value == null) {
            return null;
        }
        if (length <= 0) {
            return "";
        }
        return value.length() <= length ? value : value.substring(0, length);
    }

    public static String right(final String value, final int length) {
        if (value == null) {
            return null;
        }
        if (length <= 0) {
            return "";
        }
        return value.length() <= length ? value : value.substring(value.length() - length);
    }

    public static String abbreviate(final String value, final int maxLength) {
        if (value == null) {
            return null;
        }
        if (maxLength < 4) {
            throw new IllegalArgumentException("maxLength must be at least 4");
        }
        return value.length() <= maxLength ? value : value.substring(0, maxLength - 3) + "...";
    }

    public static String repeat(final String value, final int times) {
        if (value == null) {
            return null;
        }
        if (times <= 0) {
            return "";
        }
        return value.repeat(times);
    }

    public static String joinNonBlank(final String delimiter, final Collection<String> values) {
        final StringJoiner joiner = new StringJoiner(delimiter);
        values.stream().filter(Objects::nonNull).map(String::trim).filter(current -> !current.isEmpty()).forEach(joiner::add);
        return joiner.toString();
    }

    public static String safeSubstring(final String value, final int beginIndex, final int endIndex) {
        if (value == null) {
            return null;
        }
        final int safeBegin = Math.max(0, beginIndex);
        final int safeEnd = Math.min(value.length(), Math.max(safeBegin, endIndex));
        return value.substring(safeBegin, safeEnd);
    }
}
