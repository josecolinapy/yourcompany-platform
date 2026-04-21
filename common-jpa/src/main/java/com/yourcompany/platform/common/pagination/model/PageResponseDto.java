package com.yourcompany.platform.common.pagination.model;

import org.springframework.data.domain.Page;

import java.util.List;

/*
 * @author josec
 * @project yourcompany-platform
 */
public record PageResponseDto<T>(
        List<T> content,
        PageMetadata page
) {
    public static <T> PageResponseDto<T> fromPage(final Page<T> source) {
        return new PageResponseDto<>(
                source.getContent(),
                new PageMetadata(
                        source.getNumber(),
                        source.getSize(),
                        source.getTotalElements(),
                        source.getTotalPages(),
                        source.isFirst(),
                        source.isLast(),
                        source.isEmpty()
                )
        );
    }
}
