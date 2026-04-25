package ${package}.infraestructure.adapter.messages.consumer.dto;

import java.util.UUID;

public record ItemInboundMessage(
        UUID id,
        String name
) {
}
