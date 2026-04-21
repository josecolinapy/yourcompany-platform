package com.yourcompany.platform.common.errors.exception;

import com.yourcompany.platform.common.errors.model.ErrorCode;

/*
 * @author josec
 * @project yourcompany-platform
 */
public class ResourceNotFoundException extends PlatformException{
    public ResourceNotFoundException(final String message) {
        super(ErrorCode.RESOURCE_NOT_FOUND, message);
    }
}
