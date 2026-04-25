package com.yourcompany.platform.common.crypto.support;

import com.yourcompany.platform.common.crypto.exception.CryptoException;

import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class RsaEncryptionSupport {
    private static final String TRANSFORMATION = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";

    private RsaEncryptionSupport() {
    }

    public static byte[] encrypt(final PublicKey publicKey, final byte[] plainText) {
        try {
            final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(plainText);
        } catch (final Exception exception) {
            throw new CryptoException("Unable to encrypt RSA payload", exception);
        }
    }

    public static byte[] decrypt(final PrivateKey privateKey, final byte[] cipherText) {
        try {
            final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(cipherText);
        } catch (final Exception exception) {
            throw new CryptoException("Unable to decrypt RSA payload", exception);
        }
    }

    public static String encryptToBase64(final PublicKey publicKey, final byte[] plainText) {
        return Base64Support.encode(encrypt(publicKey, plainText));
    }

    public static byte[] decryptFromBase64(final PrivateKey privateKey, final String cipherTextBase64) {
        return decrypt(privateKey, Base64Support.decode(cipherTextBase64));
    }
}
