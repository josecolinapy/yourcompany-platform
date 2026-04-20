package com.yourcompany.platform.common.crypto.support;

import com.yourcompany.platform.common.crypto.exception.CryptoException;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class KeyMaterialSupport {
    private static final String AES = "AES";

    private KeyMaterialSupport() {
    }

    public static SecretKey aesKey(final byte[] rawKey) {
        return new SecretKeySpec(rawKey, AES);
    }

    public static SecretKey deriveAesKeyFromPassword(
            final char[] password,
            final byte[] salt,
            final int iterations,
            final int keySizeBits
    ) {
        try {
            final PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterations, keySizeBits);
            final SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            final byte[] encoded = secretKeyFactory.generateSecret(keySpec).getEncoded();
            return aesKey(encoded);
        } catch (final Exception exception) {
            throw new CryptoException("Unable to derive AES key from password", exception);
        }
    }
}
