package ru.chibisov.controller.dto;

import java.math.BigDecimal;

public class RequestMaterialDto {

    /**
     * Уникальный идентификатор записи в системе
     */
    private Long id;

    /**
     * Уникальный идентификатор заявки в системе
     */
    private Long requestId;

    /**
     * Материал, используемый в заявке
     */
    private Long materialId;

    /**
     * Количество материала для заявки
     */
    private BigDecimal quantity;

    public Long getId() {
        return id;
    }

    public RequestMaterialDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getRequestId() {
        return requestId;
    }

    public RequestMaterialDto setRequestId(Long requestId) {
        this.requestId = requestId;
        return this;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public RequestMaterialDto setMaterialId(Long materialId) {
        this.materialId = materialId;
        return this;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public RequestMaterialDto setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
        return this;
    }
}
