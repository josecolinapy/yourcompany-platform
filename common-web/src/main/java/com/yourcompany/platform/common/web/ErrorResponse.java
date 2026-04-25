package com.yourcompany.platform.common.web;

/*
 * @author josec
 * @project yourcompany-platform
 */public record ErrorResponse(
        boolean success,
        ApiError error,
        ResponseMetadata metadata
) {
    public static ErrorResponse failure(final ApiError error, final ResponseMetadata metadata) {
        return new ErrorResponse(false, error, metadata);
    }
}
