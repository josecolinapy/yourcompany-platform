package com.yourcompany.platform.common.core.text;

import java.text.Normalizer;
import java.util.Locale;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class StringNormalizer {
    private StringNormalizer() {
    }

    public static String normalize(final String value) {
        if (value == null) {
            return null;
        }
        return Normalizer.normalize(value, Normalizer.Form.NFKC).trim();
    }

    public static String normalizeToLowerCase(final String value) {
        final String normalized = normalize(value);
        return normalized == null ? null : normalized.toLowerCase(Locale.ROOT);
    }
}
