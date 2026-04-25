package ${package}.infraestructure.adapter.persistence;

import ${package}.application.ports.out.LoadItemPort;
import ${package}.application.ports.out.SaveItemPort;
import ${package}.domain.Item;
import ${package}.infraestructure.adapter.persistence.mapper.ItemPersistenceMapper;
import ${package}.infraestructure.adapter.persistence.repository.ItemRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class ItemPersistenceAdapter implements SaveItemPort, LoadItemPort {

    private final ItemRepository itemRepository;

    public ItemPersistenceAdapter(final ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item save(final Item item) {
        return ItemPersistenceMapper.toDomain(
                itemRepository.save(ItemPersistenceMapper.toEntity(item))
        );
    }

    @Override
    public Optional<Item> findById(final UUID id) {
        return itemRepository.findById(id)
                .map(ItemPersistenceMapper::toDomain);
    }
}
