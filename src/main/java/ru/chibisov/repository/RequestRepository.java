package ru.chibisov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.chibisov.model.Request;

/**
 * Интерфейс управления персистетным состоянием объектов с типом Заявка на починку {@link Request}
 */
public interface RequestRepository extends JpaRepository<Request, Long> {

}
