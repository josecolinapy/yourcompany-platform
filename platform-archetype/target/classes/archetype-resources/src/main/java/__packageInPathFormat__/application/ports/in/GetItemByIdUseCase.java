package ${package}.application.ports.in;

import ${package}.infraestructure.adapter.web.dto.response.ItemResponse;
import java.util.UUID;

public interface GetItemByIdUseCase {

    ItemResponse findById(UUID id);
}
