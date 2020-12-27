package ru.chibisov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chibisov.model.Material;

import java.util.List;

/**
 * Интерфейс управления персистетным состоянием объектов с типом Материал {@link Material}
 */
@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

    /**
     * Возвращает материал по коду артикула
     *
     * @param codeName код арктикула
     * @return найденный матераил
     */
    Material findByCodeName(String codeName);

    /**
     * Возвращает список материалов конкретного поставщика
     *
     * @param supplierId иденитификатор поставщика
     * @return найденные матераилы
     */
    List<Material> findAllBySupplierId(Long supplierId);

}
