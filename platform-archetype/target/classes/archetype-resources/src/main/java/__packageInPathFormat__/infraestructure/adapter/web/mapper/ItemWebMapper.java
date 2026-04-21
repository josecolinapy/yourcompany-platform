package ${package}.infraestructure.adapter.web.mapper;

import ${package}.domain.Item;
import ${package}.infraestructure.adapter.web.dto.response.ItemResponse;

public final class ItemWebMapper {

    private ItemWebMapper() {
    }

    public static ItemResponse toResponse(final Item item) {
        return new ItemResponse(
                item.id(),
                item.name(),
                item.description(),
                item.status(),
                item.createdAt(),
                item.updatedAt()
        );
    }
}
