package ru.chibisov.resource.dto;

import java.math.BigDecimal;

public class RequestMaterialDto {

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
