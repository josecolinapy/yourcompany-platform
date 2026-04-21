package com.yourcompany.platform.common.crypto.support;

import com.yourcompany.platform.common.crypto.exception.CryptoException;
import com.yourcompany.platform.common.crypto.model.RsaKeyPairPem;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class RsaKeyPairSupport {
    private static final String RSA = "RSA";

    private RsaKeyPairSupport() {
    }

    public static KeyPair generate2048() {
        return generate(2048);
    }

    public static KeyPair generate4096() {
        return generate(4096);
    }

    public static KeyPair generate(final int keySize) {
        try {
            final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
            keyPairGenerator.initialize(keySize, new SecureRandom());
            return keyPairGenerator.generateKeyPair();
        } catch (final Exception exception) {
            throw new CryptoException("Unable to generate RSA key pair", exception);
        }
    }

    public static RsaKeyPairPem generatePem2048() {
        return toPem(generate2048());
    }

    public static RsaKeyPairPem generatePem4096() {
        return toPem(generate4096());
    }

    public static RsaKeyPairPem toPem(final KeyPair keyPair) {
        return new RsaKeyPairPem(
                PemSupport.toPublicKeyPem(keyPair.getPublic()),
                PemSupport.toPrivateKeyPem(keyPair.getPrivate())
        );
    }
}
