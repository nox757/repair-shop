package ru.chibisov.resource.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(description = "Модель количества материала в заявке")
public class RequestMaterialDto {

    @ApiModelProperty(value = "Идентификатор заявки", example = "1")
    private Long requestId;

    @ApiModelProperty(value = "Идентификатор материала", example = "1", required = true)
    private Long materialId;

    @ApiModelProperty(value = "Количество материала, используемого в заявке", example = "12.2504", required = true)
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
