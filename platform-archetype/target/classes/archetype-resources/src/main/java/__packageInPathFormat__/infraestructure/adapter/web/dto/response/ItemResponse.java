package ${package}.infraestructure.adapter.web.dto.response;

import ${package}.domain.enum.ItemStatus;
import java.time.Instant;
import java.util.UUID;

public record ItemResponse(
        UUID id,
        String name,
        String description,
        ItemStatus status,
        Instant createdAt,
        Instant updatedAt
) {
}
