package ${package}.infraestructure.adapter.web.controller;

import ${package}.application.ports.in.CreateItemUseCase;
import ${package}.application.ports.in.GetItemByIdUseCase;
import ${package}.infraestructure.adapter.web.dto.request.CreateItemRequest;
import ${package}.infraestructure.adapter.web.dto.response.ItemResponse;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    private final CreateItemUseCase createItemUseCase;
    private final GetItemByIdUseCase getItemByIdUseCase;

    public ItemController(
            final CreateItemUseCase createItemUseCase,
            final GetItemByIdUseCase getItemByIdUseCase
    ) {
        this.createItemUseCase = createItemUseCase;
        this.getItemByIdUseCase = getItemByIdUseCase;
    }

    @PostMapping
    public ItemResponse create(@Valid @RequestBody final CreateItemRequest request) {
        return createItemUseCase.create(request);
    }

    @GetMapping("/{id}")
    public ItemResponse findById(@PathVariable final UUID id) {
        return getItemByIdUseCase.findById(id);
    }
}
