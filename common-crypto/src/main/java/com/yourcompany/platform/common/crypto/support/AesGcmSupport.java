package com.yourcompany.platform.common.crypto.support;

import com.yourcompany.platform.common.crypto.exception.CryptoException;
import com.yourcompany.platform.common.crypto.model.EncryptedPayload;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class AesGcmSupport {
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final String ALGORITHM = "AES/GCM";
    private static final int GCM_TAG_LENGTH_BITS = 128;
    private static final int IV_LENGTH_BYTES = 12;

    private AesGcmSupport() {
    }

    public static EncryptedPayload encrypt(final SecretKey secretKey, final byte[] plainText) {
        final byte[] iv = SecureRandomSupport.nextBytes(IV_LENGTH_BYTES);
        final byte[] cipherText = doEncrypt(secretKey, iv, plainText);
        return new EncryptedPayload(ALGORITHM, Base64Support.encode(iv), null, Base64Support.encode(cipherText));
    }

    public static EncryptedPayload encrypt(final SecretKey secretKey, final byte[] plainText, final byte[] salt) {
        final byte[] iv = SecureRandomSupport.nextBytes(IV_LENGTH_BYTES);
        final byte[] cipherText = doEncrypt(secretKey, iv, plainText);
        return new EncryptedPayload(ALGORITHM, Base64Support.encode(iv), Base64Support.encode(salt), Base64Support.encode(cipherText));
    }

    public static byte[] decrypt(final SecretKey secretKey, final EncryptedPayload encryptedPayload) {
        try {
            final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            final GCMParameterSpec parameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH_BITS, Base64Support.decode(encryptedPayload.iv()));
            cipher.init(Cipher.DECRYPT_MODE, secretKey, parameterSpec);
            return cipher.doFinal(Base64Support.decode(encryptedPayload.cipherText()));
        } catch (final Exception exception) {
            throw new CryptoException("Unable to decrypt AES/GCM payload", exception);
        }
    }

    private static byte[] doEncrypt(final SecretKey secretKey, final byte[] iv, final byte[] plainText) {
        try {
            final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            final GCMParameterSpec parameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH_BITS, iv);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, parameterSpec);
            return cipher.doFinal(plainText);
        } catch (final Exception exception) {
            throw new CryptoException("Unable to encrypt AES/GCM payload", exception);
        }
    }
}
