package ru.chibisov.exception;

/**
 * Класс ошибки нахождения объекта
 */
public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException() {
        super("Object not found");
    }

    public ObjectNotFoundException(String id) {
        super(String.format("Object not found with identifier %s", id));
    }
}
