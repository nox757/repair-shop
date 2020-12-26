package ru.chibisov.resource.dto;

import java.util.UUID;

/**
 * Общий обобщенный класс для описания возникающих ошибок
 */
public class ResponseError {

    /**
     * Уникальный иденитификатор ошибки
     */
    private UUID id;
    /**
     * Код ошибки
     */
    private String code;
    /**
     * Сообщение об ошибках
     */
    private String message;
    /**
     * Текущая операционная система
     */
    private String system;

    public ResponseError(UUID id, String code, String message, String system) {
        this.id = id;
        this.code = code;
        this.message = message;
        this.system = system;
    }

    public UUID getId() {
        return id;
    }

    public ResponseError setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ResponseError setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseError setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getSystem() {
        return system;
    }

    public ResponseError setSystem(String system) {
        this.system = system;
        return this;
    }
}
