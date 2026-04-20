package com.yourcompany.platform.common.crypto.support;

import java.security.SecureRandom;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class SecureRandomSupport {
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private SecureRandomSupport() {
    }

    public static byte[] nextBytes(final int size) {
        final byte[] bytes = new byte[size];
        SECURE_RANDOM.nextBytes(bytes);
        return bytes;
    }

    public static String nextBase64(final int size) {
        return Base64Support.encodeUrlSafe(nextBytes(size));
    }
}
