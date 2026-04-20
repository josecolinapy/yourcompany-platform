package com.yourcompany.platform.common.errors.exception;

import com.yourcompany.platform.common.errors.model.ErrorCode;

/*
 * @author josec
 * @project yourcompany-platform
 */
public class ValidationException extends PlatformException{
    public ValidationException(final String message) {
        super(ErrorCode.VALIDATION_ERROR, message);
    }
}
