package ru.chibisov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.chibisov.model.Request;
import ru.chibisov.model.Status;

import java.time.LocalDateTime;

/**
 * Интерфейс управления персистетным состоянием объектов с типом Заявка на починку {@link Request}
 */
public interface RequestRepository extends JpaRepository<Request, Long>, JpaSpecificationExecutor<Request> {

    /**
     * Обновление статуса у незавершенных заявок на новый с датой обновления до указанной
     * @param updatedAt дата до
     * @param status статус заявки
     */
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE Request r SET r.status = :status, r.comment = concat('archived ', r.comment)" +
            " WHERE r.updatedAt <= :updatedAt and r.status not in ('CLOSED', 'TERMINATED')")
    void updateStatusByUpdatedAtBefore(@Param("updatedAt") LocalDateTime updatedAt, @Param("status") Status status);
}
