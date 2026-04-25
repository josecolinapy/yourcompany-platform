package com.yourcompany.platform.common.crypto.model;

/*
 * @author josec
 * @project yourcompany-platform
 */public record EncryptedPayload(
        String algorithm,
        String iv,
        String salt,
        String cipherText
) {
}
