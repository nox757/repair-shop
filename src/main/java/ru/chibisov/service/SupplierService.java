package ru.chibisov.service;

import ru.chibisov.controller.dto.MaterialDto;
import ru.chibisov.controller.dto.SupplierDto;

import java.util.List;

/**
 * Интерфейс взаимодействия с поставщиками материалов
 */
public interface SupplierService {

    /**
     * Возвращает поставщика по его идентификатору
     * @param id
     * @return
     */
    SupplierDto getSupplierById(Long id);

    /**
     * Добавляет нового поставщика
     * @param supplier
     * @return
     */
    SupplierDto addSupplier(SupplierDto supplier);

    /**
     * Обновляет существующего поставщика
     * @param supplier
     * @return
     */
    SupplierDto updateSupplier(SupplierDto supplier);

    /**
     * Удаляет поставщика по его идентификатору
     * @param id
     */
    void removeSupplierById(Long id);

    /**
     * Возвращает список всех поставщиков
     * @return
     */
    List<SupplierDto> getAllSuppliers();
}
