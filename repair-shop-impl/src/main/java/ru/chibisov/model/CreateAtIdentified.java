package ru.chibisov.model;

import ru.chibisov.listener.AuditingListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingListener.class)
public abstract class CreateAtIdentified {

    @Column(updatable = false)
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    public CreateAtIdentified() {
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public CreateAtIdentified setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public CreateAtIdentified setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}

