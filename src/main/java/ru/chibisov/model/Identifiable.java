package ru.chibisov.model;

import java.io.Serializable;

/**
 * Интерфейс идентифицируемых объектов
 *
 * @param <ID> тип идентификатора объекта
 */
public interface Identifiable<ID extends Serializable> {

    /**
     * Возвращает индитификатор объекта
     */
    ID getId();
}
