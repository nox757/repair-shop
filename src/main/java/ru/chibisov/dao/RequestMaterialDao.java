package ru.chibisov.dao;

import ru.chibisov.model.RequestMaterial;
import ru.chibisov.model.RequestMaterialPk;

import java.util.Collection;
import java.util.Set;

/**
 * Интерфейс управления персистетным состоянием объектов с типом Материал-количество, используемый в заявке {@link RequestMaterial}
 */
public interface RequestMaterialDao extends GenericDao<RequestMaterial, RequestMaterialPk> {

    /**
     * Возвращает материал-количество по id заявки
     *
     * @param requestId идентификатор заявки в системе
     * @return найденное множество, используемого материала
     */
    Set<RequestMaterial> getByRequestId(Long requestId);

    /**
     * Возвращает множество возвращенных добавленных или обновленных материалов
     * @param requestMaterials идентификатор заявки в системе
     */
    Set<RequestMaterial> addOrUpdateAll(Set<RequestMaterial> requestMaterials);

    /**
     * Удаляет информацию о количестве материалов в заявке по ее идентификатору
     * @param requestId идентификатор заявки в системе
     */
    void deleteByRequestId(Long requestId);
}
