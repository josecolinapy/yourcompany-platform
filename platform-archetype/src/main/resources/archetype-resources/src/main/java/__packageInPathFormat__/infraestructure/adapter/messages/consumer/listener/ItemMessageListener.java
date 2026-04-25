package ${package}.infraestructure.adapter.messages.consumer.listener;

import ${package}.infraestructure.adapter.messages.consumer.dto.ItemInboundMessage;
import ${package}.infraestructure.adapter.messages.consumer.mapper.ItemMessageConsumerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ItemMessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemMessageListener.class);

    public void onMessage(final ItemInboundMessage message) {
        LOGGER.info("Received message: {}", ItemMessageConsumerMapper.summarize(message));
    }
}
