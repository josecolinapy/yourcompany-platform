package com.yourcompany.platform.common.crypto.support;

import com.yourcompany.platform.common.crypto.exception.CryptoException;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class RsaSignatureSupport {
    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";

    private RsaSignatureSupport() {
    }

    public static byte[] sign(final PrivateKey privateKey, final byte[] value) {
        try {
            final Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(privateKey);
            signature.update(value);
            return signature.sign();
        } catch (final Exception exception) {
            throw new CryptoException("Unable to sign RSA payload", exception);
        }
    }

    public static boolean verify(final PublicKey publicKey, final byte[] value, final byte[] signatureBytes) {
        try {
            final Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(publicKey);
            signature.update(value);
            return signature.verify(signatureBytes);
        } catch (final Exception exception) {
            throw new CryptoException("Unable to verify RSA signature", exception);
        }
    }

    public static String signToBase64(final PrivateKey privateKey, final byte[] value) {
        return Base64Support.encode(sign(privateKey, value));
    }

    public static boolean verifyFromBase64(final PublicKey publicKey, final byte[] value, final String signatureBase64) {
        return verify(publicKey, value, Base64Support.decode(signatureBase64));
    }
}
