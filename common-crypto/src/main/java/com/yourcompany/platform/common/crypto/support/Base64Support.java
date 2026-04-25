package com.yourcompany.platform.common.crypto.support;

import java.util.Base64;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class Base64Support {
    private Base64Support() {
    }

    public static String encode(final byte[] value) {
        return Base64.getEncoder().encodeToString(value);
    }

    public static String encodeUrlSafe(final byte[] value) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(value);
    }

    public static byte[] decode(final String value) {
        return Base64.getDecoder().decode(value);
    }

    public static byte[] decodeUrlSafe(final String value) {
        return Base64.getUrlDecoder().decode(value);
    }
}
