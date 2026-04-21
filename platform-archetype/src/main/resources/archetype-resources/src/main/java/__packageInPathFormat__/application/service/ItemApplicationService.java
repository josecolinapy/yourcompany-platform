package ${package}.application.service;

import ${package}.application.ports.in.CreateItemUseCase;
import ${package}.application.ports.in.GetItemByIdUseCase;
import ${package}.application.ports.out.LoadItemPort;
import ${package}.application.ports.out.PublishItemCreatedPort;
import ${package}.application.ports.out.SaveItemPort;
import ${package}.domain.Item;
import ${package}.domain.enum.ItemStatus;
import ${package}.domain.event.ItemCreatedEvent;
import ${package}.domain.exception.ItemNotFoundException;
import ${package}.infraestructure.adapter.web.dto.request.CreateItemRequest;
import ${package}.infraestructure.adapter.web.dto.response.ItemResponse;
import ${package}.infraestructure.adapter.web.mapper.ItemWebMapper;
import com.yourcompany.platform.common.aop.annotation.Logged;
import com.yourcompany.platform.common.aop.annotation.Timed;
import java.time.Instant;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemApplicationService implements CreateItemUseCase, GetItemByIdUseCase {

    private final SaveItemPort saveItemPort;
    private final LoadItemPort loadItemPort;
    private final PublishItemCreatedPort publishItemCreatedPort;

    public ItemApplicationService(
            final SaveItemPort saveItemPort,
            final LoadItemPort loadItemPort,
            final PublishItemCreatedPort publishItemCreatedPort
    ) {
        this.saveItemPort = saveItemPort;
        this.loadItemPort = loadItemPort;
        this.publishItemCreatedPort = publishItemCreatedPort;
    }

    @Override
    @Logged
    @Timed(value = "item.create")
    public ItemResponse create(final CreateItemRequest request) {
        final Instant now = Instant.now();
        final Item item = new Item(
                UUID.randomUUID(),
                request.name(),
                request.description(),
                ItemStatus.ACTIVE,
                now,
                now
        );

        final Item saved = saveItemPort.save(item);
        publishItemCreatedPort.publish(new ItemCreatedEvent(saved.id(), saved.name(), saved.createdAt()));
        return ItemWebMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ItemResponse findById(final UUID id) {
        return loadItemPort.findById(id)
                .map(ItemWebMapper::toResponse)
                .orElseThrow(() -> new ItemNotFoundException("Item not found"));
    }
}
