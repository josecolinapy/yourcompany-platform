package com.yourcompany.platform.common.crypto.support;

import com.yourcompany.platform.common.crypto.exception.CryptoException;

import java.nio.charset.StandardCharsets;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class HmacSupport {
    private HmacSupport() {
    }

    public static byte[] hmacSha256(final byte[] key, final byte[] value) {
        return sign("HmacSHA256", key, value);
    }

    public static byte[] hmacSha512(final byte[] key, final byte[] value) {
        return sign("HmacSHA512", key, value);
    }

    public static String hmacSha256Base64(final byte[] key, final String value) {
        return Base64Support.encode(hmacSha256(key, value.getBytes(StandardCharsets.UTF_8)));
    }

    public static String hmacSha512Base64(final byte[] key, final String value) {
        return Base64Support.encode(hmacSha512(key, value.getBytes(StandardCharsets.UTF_8)));
    }

    private static byte[] sign(final String algorithm, final byte[] key, final byte[] value) {
        try {
            final Mac mac = Mac.getInstance(algorithm);
            mac.init(new SecretKeySpec(key, algorithm));
            return mac.doFinal(value);
        } catch (final Exception exception) {
            throw new CryptoException("Unable to calculate HMAC with algorithm: " + algorithm, exception);
        }
    }
}
