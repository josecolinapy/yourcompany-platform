package ${package}.infraestructure.adapter.persistence.entity;

import com.yourcompany.platform.common.jpa.entity.AuditableEntity;
import ${package}.domain.enum.ItemStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "item")
public class ItemEntity extends AuditableEntity {

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private ItemStatus status;

    public String getName() { return name; }
    public void setName(final String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(final String description) { this.description = description; }
    public ItemStatus getStatus() { return status; }
    public void setStatus(final ItemStatus status) { this.status = status; }
}
