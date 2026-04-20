package com.yourcompany.platform.common.crypto.model;

/*
 * @author josec
 * @project yourcompany-platform
 */public record RsaKeyPairPem(
        String publicKeyPem,
        String privateKeyPem
) {
}
