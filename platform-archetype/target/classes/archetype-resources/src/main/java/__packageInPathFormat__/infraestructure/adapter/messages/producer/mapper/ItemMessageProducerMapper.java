package ${package}.infraestructure.adapter.messages.producer.mapper;

import ${package}.domain.event.ItemCreatedEvent;
import ${package}.infraestructure.adapter.messages.producer.dto.ItemCreatedMessage;

public final class ItemMessageProducerMapper {

    private ItemMessageProducerMapper() {
    }

    public static ItemCreatedMessage toMessage(final ItemCreatedEvent event) {
        return new ItemCreatedMessage(event.id(), event.name(), event.createdAt());
    }
}
