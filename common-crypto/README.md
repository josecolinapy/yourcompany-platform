## `common-crypto`

**Tipo:** Criptografía reusable

**Qué resuelve:** Incluye hashing, HMAC, AES/GCM, PEM y soporte RSA para generación de llaves, cifrado y firma.

**Cuándo usarlo:** Genera un `KeyPair` con `RsaKeyPairSupport`, firma con `RsaSignatureSupport` y cifra con `AesGcmSupport`.

**Ejemplo de uso:**

```java
KeyPair keyPair = RsaKeyPairSupport.generate2048();
String signature = RsaSignatureSupport.signToBase64(
        keyPair.getPrivate(),
        "payload".getBytes(StandardCharsets.UTF_8)
);
boolean valid = RsaSignatureSupport.verifyFromBase64(
        keyPair.getPublic(),
        "payload".getBytes(StandardCharsets.UTF_8),
        signature
);
```