package ${package}.application.ports.in;

import ${package}.infraestructure.adapter.web.dto.request.CreateItemRequest;
import ${package}.infraestructure.adapter.web.dto.response.ItemResponse;

public interface CreateItemUseCase {

    ItemResponse create(CreateItemRequest request);
}
