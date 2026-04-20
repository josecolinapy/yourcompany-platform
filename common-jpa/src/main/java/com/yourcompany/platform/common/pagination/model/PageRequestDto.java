package com.yourcompany.platform.common.pagination.model;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/*
 * @author josec
 * @project yourcompany-platform
 */public record PageRequestDto(
        int page,
        int size,
        String sortBy,
        String direction
) {
    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_SIZE = 20;
    private static final String DEFAULT_SORT_BY = "createdAt";
    private static final String DEFAULT_DIRECTION = "DESC";

    public Pageable toPageable() {
        final int resolvedPage = page < 0 ? DEFAULT_PAGE : page;
        final int resolvedSize = size <= 0 ? DEFAULT_SIZE : size;
        final String resolvedSortBy = isBlank(sortBy) ? DEFAULT_SORT_BY : sortBy;
        final String resolvedDirection = isBlank(direction) ? DEFAULT_DIRECTION : direction;
        final Sort.Direction sortDirection = Sort.Direction.fromString(resolvedDirection);
        return PageRequest.of(resolvedPage, resolvedSize, Sort.by(sortDirection, resolvedSortBy));
    }

    public static PageRequestDto defaultRequest() {
        return new PageRequestDto(DEFAULT_PAGE, DEFAULT_SIZE, DEFAULT_SORT_BY, DEFAULT_DIRECTION);
    }

    private static boolean isBlank(final String value) {
        return value == null || value.isBlank();
    }
}
