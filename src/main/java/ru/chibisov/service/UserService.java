package ru.chibisov.service;

import ru.chibisov.controller.dto.UserDto;

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
}
