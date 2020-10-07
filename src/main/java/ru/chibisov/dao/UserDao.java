package ru.chibisov.dao;

import ru.chibisov.model.User;

import java.util.List;

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
