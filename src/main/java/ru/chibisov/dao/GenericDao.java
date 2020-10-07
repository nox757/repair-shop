package ru.chibisov.dao;

import ru.chibisov.model.Identifiable;

import java.io.Serializable;
import java.util.Collection;

/**
 * Обощий интерфейс управления персистентным состоянием объектов
 *
 * @param <T>  тип объекта персистенции
 * @param <ID> тип первичного ключа
 */
public interface GenericDao<T extends Identifiable<ID>, ID extends Serializable> {

    /**
     * Создание новой записи
     *
     * @param object записываеемый объект
     * @return сохраненный объект
     */
    T create(T object);

    /**
     * Возвращает объект по его первичному ключу
     *
     * @param id первичный ключ
     * @return найденный объект
     */
    T getById(ID id);

    /**
     * Обновляет запись объекта
     *
     * @param object обновляемый объект
     * @return обновленный объект
     */
    T update(T object);

    /**
     * Удаляет запись об объекте
     *
     * @param object удаляемый объект
     * @return удаленный объект
     */
    T delete(T object);

    /**
     * Удаляет запись об объекте по первичному ключу
     *
     * @param key первичный ключ
     * @return удаленный объект
     */
    T deleteById(ID key);

    /**
     * Возвращает коллекцию объектов, соответствующих всем записям
     */
    Collection<T> getAll();

    /**
     * Добавляет записи об объектах коллекции
     *
     * @param objects коллекция объектов
     * @return коллекция добавленных объектов
     */
    Collection<T> addAll(Collection<T> objects);
}
