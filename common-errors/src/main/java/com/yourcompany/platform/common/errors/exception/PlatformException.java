package com.yourcompany.platform.common.errors.exception;

import com.yourcompany.platform.common.errors.model.ErrorCode;

/*
 * @author josec
 * @project yourcompany-platform
 */
public class PlatformException extends RuntimeException{
    private final ErrorCode errorCode;

    protected PlatformException(final ErrorCode errorCode, final String message) {
        super(message);
        this.errorCode = errorCode;
    }

    protected PlatformException(final ErrorCode errorCode, final String message, final Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
