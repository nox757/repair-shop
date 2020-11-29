package ru.chibisov.exception;

/**
 * Класс ошибки указывающий на проблемы,
 * возникающии при нахождении смежных объектов
 */
public class BadDataFieldException extends RuntimeException {

    public BadDataFieldException() {
        super("One or more field contains error");
    }

    public BadDataFieldException(String message) {
        super(String.format("One or more field contains error - %s", message));
    }
}
