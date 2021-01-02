package ru.chibisov.resource.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(description = "Модель материала")
public class MaterialDto {

    @ApiModelProperty(value = "Идентификатор материала", example = "1")
    private Long id;

    @ApiModelProperty(value = "Кодовое имя материала", example = "AA02-32/10-5", required = true)
    private String codeName;

    @ApiModelProperty(value = "Название материала", example = "Сталь", required = true)
    private String name;

    @ApiModelProperty(value = "Цена за единицу", example = "25.02", required = true)
    private BigDecimal price;

    @ApiModelProperty(value = "Текущий остаток", example = "4526.0251", required = true)
    private BigDecimal remains;

    @ApiModelProperty(value = "Идентификатор поставщика", example = "46", required = true)
    private Long supplierId;

    public Long getId() {
        return id;
    }

    public MaterialDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCodeName() {
        return codeName;
    }

    public MaterialDto setCodeName(String codeName) {
        this.codeName = codeName;
        return this;
    }

    public String getName() {
        return name;
    }

    public MaterialDto setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public MaterialDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public BigDecimal getRemains() {
        return remains;
    }

    public MaterialDto setRemains(BigDecimal remains) {
        this.remains = remains;
        return this;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public MaterialDto setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
        return this;
    }
}
