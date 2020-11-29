package ru.chibisov.dao;

import ru.chibisov.model.Material;

import java.math.BigDecimal;
import java.util.List;

/**
 * Интерфейс управления персистетным состоянием объектов с типом Материал {@link Material}
 */
public interface MaterialDao extends GenericDao<Material, Long> {

    /**
     * Возвращает материал по коду артикула
     *
     * @param codeName код арктикула
     * @return найденный матераил
     */
    Material getByCodeName(String codeName);

    /**
     * Возвращает список материалов конкретного поставщика
     *
     * @param supplierId иденитификатор поставщика
     * @return найденные матераилы
     */
    List<Material> getBySupplierId(Long supplierId);

}
