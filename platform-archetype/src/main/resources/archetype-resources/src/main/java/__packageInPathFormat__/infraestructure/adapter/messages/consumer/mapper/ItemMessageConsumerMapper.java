package ${package}.infraestructure.adapter.messages.consumer.mapper;

import ${package}.infraestructure.adapter.messages.consumer.dto.ItemInboundMessage;

public final class ItemMessageConsumerMapper {

    private ItemMessageConsumerMapper() {
    }

    public static String summarize(final ItemInboundMessage message) {
        return "Inbound item message[id=" + message.id() + ", name=" + message.name() + "]";
    }
}
