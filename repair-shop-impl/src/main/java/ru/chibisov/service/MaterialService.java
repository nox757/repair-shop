package ru.chibisov.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.chibisov.resource.dto.MaterialDto;
import ru.chibisov.resource.dto.search.MaterialSearchDto;

import java.util.List;

/**
 * Интерфейс взаимодействия с материалом
 */
public interface MaterialService {

    /**
     * Возвращает найденный материал по идентификатору
     * @param id
     * @return
     */
    MaterialDto getMaterialById(Long id);

    /**
     * Добавляет новый материал
     */
    MaterialDto addMaterial(MaterialDto material);

    /**
     * Обновляет существующий существующий материал
     * @param material
     * @return
     */
    MaterialDto updateMaterial(MaterialDto material);

    /**
     * Удаляет материал по его иденитификатору
     * @param id
     * @return
     */
    void removeMaterialById(Long id);

    /**
     * Возвращает список всех материалов
     * @return
     */
    List<MaterialDto> getAllMaterials();

    /**
     * Возвращает список всех материалов указанного поставщика
     * @return
     */
    List<MaterialDto> getAllMaterialsBySupplierId(Long supplierId);

    /**
     * Возвращает страницу со списоком материалов
     * @param materialSearchDto поля фильтрации
     * @param pageable информация о разбиение и сортировке
     * @return страница со списком отфильтрованных материалов
     */
    Page<MaterialDto> getMaterials(MaterialSearchDto materialSearchDto, Pageable pageable);
}
