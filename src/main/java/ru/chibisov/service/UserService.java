package ru.chibisov.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.chibisov.controller.dto.UserDto;
import ru.chibisov.controller.dto.search.UserSearchDto;

import java.util.List;

/**
 * Интерфейс взаимодействия с пользователями
 */
public interface UserService {

    /**
     * Возвращает пользователя по его идентификатору
     * @param id
     * @return
     */
    UserDto getUserById(Long id);

    /**
     * Добавляет нового пользователя
     * @param user
     * @return
     */
    UserDto addUser(UserDto user);

    /**
     * Обновляет информацию о существующем пользователе
     * @param user
     * @return
     */
    UserDto updateUser(UserDto user);

    /**
     * Удаляет пользователя по его идентификатору
     * @param id
     */
    void removeUserById(Long id);

    /**
     * Возвращает список всех пользователей
     * @return
     */
    List<UserDto> getAllUsers();

    /**
     * Возвращает страницу со списком пользователей
     * @param userSearchDto поля для фильтрации
     * @param pageable информации о разбиении и сортировки
     * @return страница со списком отфильтрованных пользователей
     */
    Page<UserDto> getUsers(UserSearchDto userSearchDto, Pageable pageable);
}
