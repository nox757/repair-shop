package ru.chibisov.dao.impl;

import ru.chibisov.dao.GenericDao;
import ru.chibisov.model.Identifiable;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

public abstract class AbstractDao<T extends Identifiable<ID>, ID extends Serializable> implements GenericDao<T, ID> {

    private Class<T> clazz;

    protected Map<ID, T> elements;

    public AbstractDao(Class<T> elementClass, Map<ID, T> map) {
        this.clazz = elementClass;
        this.elements = map;
    }

    @Override
    public T create(T object) {
        elements.put(object.getId(), object);
        return object;
    }

    @Override
    public T getById(ID key) {
        return elements.get(key);
    }

    @Override
    public T update(T object) {
        elements.put(object.getId(), object);
        return object;
    }

    @Override
    public T delete(T object) {
        return elements.remove(object.getId());
    }

    @Override
    public Collection<T> getAll() {
        return elements.values();
    }

    @Override
    public Collection<T> addAll(Collection<T> objects) {
        for (T object : objects) {
            elements.put(object.getId(), object);
        }
        return objects;
    }

    @Override
    public T deleteById(ID key) {
        return elements.remove(key);
    }
}
