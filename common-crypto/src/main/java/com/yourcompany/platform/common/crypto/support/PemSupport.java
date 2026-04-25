package com.yourcompany.platform.common.crypto.support;

import com.yourcompany.platform.common.crypto.exception.CryptoException;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class PemSupport {
    private static final String RSA = "RSA";
    private static final String PUBLIC_KEY_BEGIN = "-----BEGIN PUBLIC KEY-----";
    private static final String PUBLIC_KEY_END = "-----END PUBLIC KEY-----";
    private static final String PRIVATE_KEY_BEGIN = "-----BEGIN PRIVATE KEY-----";
    private static final String PRIVATE_KEY_END = "-----END PRIVATE KEY-----";

    private PemSupport() {
    }

    public static String toPublicKeyPem(final PublicKey publicKey) {
        return toPem(PUBLIC_KEY_BEGIN, PUBLIC_KEY_END, publicKey.getEncoded());
    }

    public static String toPrivateKeyPem(final PrivateKey privateKey) {
        return toPem(PRIVATE_KEY_BEGIN, PRIVATE_KEY_END, privateKey.getEncoded());
    }

    public static PublicKey readPublicKey(final String pem) {
        try {
            final byte[] decoded = decodePem(pem, PUBLIC_KEY_BEGIN, PUBLIC_KEY_END);
            final X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);
            return KeyFactory.getInstance(RSA).generatePublic(keySpec);
        } catch (final Exception exception) {
            throw new CryptoException("Unable to parse RSA public key PEM", exception);
        }
    }

    public static PrivateKey readPrivateKey(final String pem) {
        try {
            final byte[] decoded = decodePem(pem, PRIVATE_KEY_BEGIN, PRIVATE_KEY_END);
            final PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);
            return KeyFactory.getInstance(RSA).generatePrivate(keySpec);
        } catch (final Exception exception) {
            throw new CryptoException("Unable to parse RSA private key PEM", exception);
        }
    }

    private static String toPem(final String begin, final String end, final byte[] encoded) {
        final String base64 = java.util.Base64.getMimeEncoder(64, "".getBytes())
                        .encodeToString(encoded);

        return begin + "" + base64 + "" + end;
    }

    private static byte[] decodePem(final String pem, final String begin, final String end) {
        final String normalized = pem
                .replace(begin, "")
                .replace(end, "")
                .replaceAll("\s", "");

        return java.util.Base64.getDecoder().decode(normalized);
    }
}
