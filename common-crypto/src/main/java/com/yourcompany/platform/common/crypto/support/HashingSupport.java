package com.yourcompany.platform.common.crypto.support;

import com.yourcompany.platform.common.crypto.exception.CryptoException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class HashingSupport {
    private HashingSupport() {
    }

    public static byte[] sha256(final byte[] value) {
        return digest("SHA-256", value);
    }

    public static byte[] sha512(final byte[] value) {
        return digest("SHA-512", value);
    }

    public static String sha256Hex(final String value) {
        return toHex(sha256(value.getBytes(StandardCharsets.UTF_8)));
    }

    public static String sha512Hex(final String value) {
        return toHex(sha512(value.getBytes(StandardCharsets.UTF_8)));
    }

    public static boolean constantTimeEquals(final byte[] left, final byte[] right) {
        return MessageDigest.isEqual(left, right);
    }

    public static boolean constantTimeEquals(final String left, final String right) {
        if (left == null || right == null) {
            return false;
        }
        return constantTimeEquals(left.getBytes(StandardCharsets.UTF_8), right.getBytes(StandardCharsets.UTF_8));
    }

    private static byte[] digest(final String algorithm, final byte[] value) {
        try {
            final MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            return messageDigest.digest(value);
        } catch (final NoSuchAlgorithmException exception) {
            throw new CryptoException("Hash algorithm not available: " + algorithm, exception);
        }
    }

    private static String toHex(final byte[] value) {
        final StringBuilder builder = new StringBuilder(value.length * 2);
        for (final byte current : value) {
            builder.append(String.format("%02x", current));
        }
        return builder.toString();
    }
}
