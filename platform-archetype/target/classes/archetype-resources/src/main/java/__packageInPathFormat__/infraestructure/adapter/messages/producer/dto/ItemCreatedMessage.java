package ${package}.infraestructure.adapter.messages.producer.dto;

import java.time.Instant;
import java.util.UUID;

public record ItemCreatedMessage(
        UUID id,
        String name,
        Instant createdAt
) {
}
