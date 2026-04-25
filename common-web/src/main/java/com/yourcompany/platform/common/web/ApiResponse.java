package com.yourcompany.platform.common.web;

/*
 * @author josec
 * @project yourcompany-platform
 */public record ApiResponse<T>(
        boolean success,
        T data,
        ResponseMetadata metadata
) {
    public static <T> ApiResponse<T> success(final T data, final ResponseMetadata metadata) {
        return new ApiResponse<>(true, data, metadata);
    }
}
