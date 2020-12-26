package ru.chibisov.exception;

import org.springframework.validation.Errors;

/**
 * Класс ошибки возникающей на этапе валидации
 */
public class ObjectDtoValidationException extends RuntimeException {

    private final Errors errors;

    public ObjectDtoValidationException(Errors errors) {
        super(String.format("Object %s contains %d errors", errors.getObjectName(), errors.getErrorCount()));
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }

}
