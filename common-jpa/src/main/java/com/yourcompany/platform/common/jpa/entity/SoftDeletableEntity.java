package com.yourcompany.platform.common.jpa.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

/*
 * @author josec
 * @project yourcompany-platform
 */
@MappedSuperclass
public class SoftDeletableEntity extends AuditableEntity{
    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    public boolean isDeleted() {
        return deleted;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public void markDeleted(final Instant timestamp) {
        this.deleted = true;
        this.deletedAt = timestamp;
    }
}
