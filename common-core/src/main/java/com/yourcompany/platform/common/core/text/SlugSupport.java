package com.yourcompany.platform.common.core.text;

import java.text.Normalizer;
import java.util.Locale;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class SlugSupport {
    private SlugSupport() {
    }

    public static String slugify(final String value) {
        if (StringSupport.isBlank(value)) {
            return "";
        }
        String normalized = Normalizer.normalize(value, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase(Locale.ROOT)
                .trim();
        normalized = normalized.replaceAll("[^a-z0-9]+", "-");
        normalized = normalized.replaceAll("(^-+|-+$)", "");
        return normalized;
    }
}
