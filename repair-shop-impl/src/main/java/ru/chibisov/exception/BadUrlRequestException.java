package ru.chibisov.exception;

/**
 * Класс ошибки указывыющий на проблемы в запросе
 */
public class BadUrlRequestException extends RuntimeException {

    public BadUrlRequestException() {
        super("Bad request");
    }

    public BadUrlRequestException(String message) {
        super(String.format("Bad request - %s", message));
    }
}
