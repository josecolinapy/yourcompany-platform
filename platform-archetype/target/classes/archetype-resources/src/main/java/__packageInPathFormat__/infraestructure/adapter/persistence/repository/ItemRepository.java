package ${package}.infraestructure.adapter.persistence.repository;

import ${package}.infraestructure.adapter.persistence.entity.ItemEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, UUID> {
}
