package ru.chibisov.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.chibisov.controller.dto.SupplierDto;
import ru.chibisov.controller.dto.search.SupplierSearchDto;

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

    /**
     * Возвращает страницу со списком поставщиков
     * @param supplierSearchDto поля для фильтрации
     * @param pageable ифнормации о разбиении и сортировки
     * @return страница со списком отфильтрованных поставщиков
     */
    Page<SupplierDto> getSuppliers(SupplierSearchDto supplierSearchDto, Pageable pageable);
}
