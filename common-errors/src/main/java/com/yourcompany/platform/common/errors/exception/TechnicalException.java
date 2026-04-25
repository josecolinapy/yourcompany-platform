package com.yourcompany.platform.common.errors.exception;

import com.yourcompany.platform.common.errors.model.ErrorCode;

/*
 * @author josec
 * @project yourcompany-platform
 */
public class TechnicalException extends PlatformException{
    public TechnicalException(final String message) {
        super(ErrorCode.TECHNICAL_ERROR, message);
    }

    public TechnicalException(final String message, final Throwable cause) {
        super(ErrorCode.TECHNICAL_ERROR, message, cause);
    }

    public TechnicalException(final ErrorCode errorCode, final String message, final Throwable cause) {
        super(errorCode, message, cause);
    }
}
