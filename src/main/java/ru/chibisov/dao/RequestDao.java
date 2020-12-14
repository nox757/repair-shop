package ru.chibisov.dao;

import ru.chibisov.model.Request;

/**
 * Интерфейс управления персистетным состоянием объектов с типом Заявка на починку {@link Request}
 */
public interface RequestDao extends GenericDao<Request, Long> {

}
