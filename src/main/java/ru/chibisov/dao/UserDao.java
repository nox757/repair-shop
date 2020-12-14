package ru.chibisov.dao;

import ru.chibisov.model.User;

import java.util.List;

/**
 * Интерфейс управления персистетным состоянием объектов с типом Пользователь {@link User}
 */
public interface UserDao extends GenericDao<User, Long> {
    /**
     * Возвращает коллекцию всех клиентов
     */
    List<User> getAllCustomer();

    /**
     * Возвращает коллецию всех мастеров
     */
    List<User> getAllRepairer();

}
