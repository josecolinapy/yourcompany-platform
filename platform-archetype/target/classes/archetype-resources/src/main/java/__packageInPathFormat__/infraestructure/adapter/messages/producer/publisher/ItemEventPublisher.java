package ${package}.infraestructure.adapter.messages.producer.publisher;

import ${package}.application.ports.out.PublishItemCreatedPort;
import ${package}.domain.event.ItemCreatedEvent;
import ${package}.infraestructure.adapter.messages.producer.mapper.ItemMessageProducerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ItemEventPublisher implements PublishItemCreatedPort {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemEventPublisher.class);

    @Override
    public void publish(final ItemCreatedEvent event) {
        LOGGER.info("Publishing item created event: {}", ItemMessageProducerMapper.toMessage(event));
    }
}
