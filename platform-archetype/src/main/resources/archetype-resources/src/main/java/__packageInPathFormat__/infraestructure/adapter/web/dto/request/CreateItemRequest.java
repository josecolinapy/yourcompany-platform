package ${package}.infraestructure.adapter.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateItemRequest(
        @NotBlank
        @Size(max = 150)
        String name,

        @Size(max = 500)
        String description
) {
}
