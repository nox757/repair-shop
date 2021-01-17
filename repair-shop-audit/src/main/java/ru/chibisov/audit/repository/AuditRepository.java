package ru.chibisov.audit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chibisov.audit.model.AuditEntity;

/**
 * Интерфейс управления персистетным состоянием объектов с типом Событие аудита {@link AuditEntity}
 */
@Repository
public interface AuditRepository extends JpaRepository<AuditEntity, Long> {
}
