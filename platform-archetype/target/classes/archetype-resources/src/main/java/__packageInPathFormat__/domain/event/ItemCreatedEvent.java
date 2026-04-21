package ${package}.domain.event;

import java.time.Instant;
import java.util.UUID;

public record ItemCreatedEvent(
        UUID id,
        String name,
        Instant createdAt
) {
}
