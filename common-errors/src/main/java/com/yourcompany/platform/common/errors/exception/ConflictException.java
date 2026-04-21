package com.yourcompany.platform.common.errors.exception;

import com.yourcompany.platform.common.errors.model.ErrorCode;

/*
 * @author josec
 * @project yourcompany-platform
 */
public class ConflictException extends PlatformException {
    public ConflictException(final String message) {
        super(ErrorCode.CONFLICT, message);
    }
}
