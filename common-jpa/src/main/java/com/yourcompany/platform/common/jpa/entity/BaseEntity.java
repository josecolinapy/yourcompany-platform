package com.yourcompany.platform.common.jpa.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.*;

/*
 * @author josec
 * @project yourcompany-platform
 */
@MappedSuperclass
public abstract class  BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    public UUID getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }

    @PrePersist
    protected void generateId() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BaseEntity that)) {
            return false;
        }
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
