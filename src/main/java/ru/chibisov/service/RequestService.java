package ru.chibisov.service;

import ru.chibisov.controller.dto.RequestDto;
import ru.chibisov.controller.dto.RequestMaterialDto;

import java.util.List;

/**
 * Интерфейс взаимодействия с заявкой
 */
public interface RequestService {

    /**
     * Возвращает заявку по ее идентификатору
     * @param id
     * @return
     */
    RequestDto getRequestById(Long id);

    /**
     * Добавляет новую заявку
     * @param requestDto
     * @return
     */
    RequestDto addRequest(RequestDto requestDto);

    /**
     * Обновляет существующую заявку
     * @param requestDto
     * @return
     */
    RequestDto updateRequest(RequestDto requestDto);

    /**
     * Удаляет заявку по ее идентификатору
     * @param id
     */
    void removeRequestById(Long id);

    /**
     * Возвращает список всех хранящихся заявок
     * @return
     */
    List<RequestDto> getAllRequests();

    /**
     * Добавляет новые материалы к заявке
     * @return обновленную заявку с прикрепленнымы к ней материалами
     */
    List<RequestDto> addMaterials(List<RequestMaterialDto> materialDtos);

}
