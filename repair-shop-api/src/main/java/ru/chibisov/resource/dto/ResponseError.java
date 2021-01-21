package ru.chibisov.resource.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.UUID;

/**
 * Общий обобщенный класс для описания возникающих ошибок
 */
@ApiModel(description = "Модель ответа в результате ошибки")
public class ResponseError {

    @ApiModelProperty(value = "Идентификатор ошибки", example = "30ff2b2b-42dc-4a26-93c3-ec312b4819f8", required = true)
    private UUID id;

    @ApiModelProperty(value = "Код ошибки", example = "unknown", required = true)
    private String code;

    @ApiModelProperty(value = "Сообщение об ошибке", example = "Not found", required = true)
    private String message;

    @ApiModelProperty(value = "Идентификатор системы", example = "system-example", required = true)
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

    @Override
    public String toString() {
        return "ResponseError{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", system='" + system + '\'' +
                '}';
    }
}
