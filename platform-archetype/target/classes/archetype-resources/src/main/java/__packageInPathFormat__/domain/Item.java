package ${package}.domain;

import ${package}.domain.enum.ItemStatus;
import java.time.Instant;
import java.util.UUID;

public record Item(
        UUID id,
        String name,
        String description,
        ItemStatus status,
        Instant createdAt,
        Instant updatedAt
) {
}
