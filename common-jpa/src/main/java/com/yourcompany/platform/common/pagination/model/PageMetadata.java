package com.yourcompany.platform.common.pagination.model;

/*
 * @author josec
 * @project yourcompany-platform
 */public record PageMetadata(
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean first,
        boolean last,
        boolean empty
) {
}
