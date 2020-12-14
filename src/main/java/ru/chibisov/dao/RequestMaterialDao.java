package ru.chibisov.dao;

import ru.chibisov.model.RequestMaterial;

import java.util.Set;

/**
 * Интерфейс управления персистетным состоянием объектов с типом Материал-количество, используемый в заявке {@link RequestMaterial}
 */
public interface RequestMaterialDao extends GenericDao<RequestMaterial, Long> {

    /**
     * Возвращает материал-количество по id заявки
     *
     * @param requestId идентификатор заявки в системе
     * @return найденное множество, используемого материала
     */
    Set<RequestMaterial> getByRequestId(Long requestId);
}
