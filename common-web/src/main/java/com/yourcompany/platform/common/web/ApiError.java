package com.yourcompany.platform.common.web;

import com.yourcompany.platform.common.errors.model.ErrorCode;

import java.util.Map;

/*
 * @author josec
 * @project yourcompany-platform
 */public record ApiError(
        ErrorCode code,
        String message,
        Map<String, Object> attributes
) {
}
