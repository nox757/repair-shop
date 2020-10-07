package ru.chibisov.dao;

import ru.chibisov.model.Material;

import java.math.BigDecimal;

public interface MaterialDao extends GenericDao<Material, Long> {

    /**
     * Возвращает материал по коду артикула
     *
     * @param codeName код арктикула
     * @return найденный матераил
     */
    Material getByCodeName(String codeName);

    /**
     * Обновляет информацию об остатках
     *
     * @param codeName код артикула
     * @param remains  новое значения остатка
     * @return обновленный материал
     */
    Material updateRemains(String codeName, BigDecimal remains);

}
