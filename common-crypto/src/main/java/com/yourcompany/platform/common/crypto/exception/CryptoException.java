package com.yourcompany.platform.common.crypto.exception;

/*
 * @author josec
 * @project yourcompany-platform
 */
public class CryptoException extends RuntimeException{
    public CryptoException(final String message) {
        super(message);
    }

    public CryptoException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
