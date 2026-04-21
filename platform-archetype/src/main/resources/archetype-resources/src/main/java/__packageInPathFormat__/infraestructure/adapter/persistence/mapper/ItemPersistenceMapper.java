package ${package}.infraestructure.adapter.persistence.mapper;

import ${package}.domain.Item;
import ${package}.infraestructure.adapter.persistence.entity.ItemEntity;

public final class ItemPersistenceMapper {

    private ItemPersistenceMapper() {
    }

    public static ItemEntity toEntity(final Item item) {
        final ItemEntity entity = new ItemEntity();
        entity.setId(item.id());
        entity.setName(item.name());
        entity.setDescription(item.description());
        entity.setStatus(item.status());
        entity.setCreatedAt(item.createdAt());
        entity.setUpdatedAt(item.updatedAt());
        return entity;
    }

    public static Item toDomain(final ItemEntity entity) {
        return new Item(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
