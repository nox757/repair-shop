package ru.chibisov.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.chibisov.controller.dto.RequestDto;
import ru.chibisov.controller.dto.search.RequestSearchDto;

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
     * Возвращает страницу со списком заявок
     * @param requestSearchDto поля для фильтрации
     * @param pageable информации о разбиении и сортеровке
     * @return страница со списком отфильрованных материалов
     */
    Page<RequestDto> getRequests(RequestSearchDto requestSearchDto, Pageable pageable);
}
