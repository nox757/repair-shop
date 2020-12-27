package ru.chibisov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.chibisov.model.RequestMaterial;
import ru.chibisov.model.RequestMaterialPk;

import java.util.Set;

/**
 * Интерфейс управления персистетным состоянием объектов с типом Материал-количество, используемый в заявке {@link RequestMaterial}
 */
public interface RequestMaterialRepository extends JpaRepository<RequestMaterial, RequestMaterialPk> {

    /**
     * Возвращает материал-количество по id заявки
     *
     * @param requestId идентификатор заявки в системе
     * @return найденное множество, используемого материала
     */
    @Query("SELECT r FROM RequestMaterial r WHERE r.id.requestId = :requestId")
    Set<RequestMaterial> findByRequestId(@Param("requestId") Long requestId);

    /**
     * Удаляет информацию о количестве материалов в заявке по ее идентификатору
     * @param requestId идентификатор заявки в системе
     */
    @Modifying
    @Query("DELETE FROM RequestMaterial r WHERE r.id.requestId = :requestId")
    void deleteByRequestId(@Param("requestId") Long requestId);
}
