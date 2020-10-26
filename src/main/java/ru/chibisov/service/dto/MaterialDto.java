package ru.chibisov.service.dto;

import java.math.BigDecimal;

public class MaterialDto {

    /**
     * Идентификатор материаала в системе
     */
    private Long id;

    /**
     * Уникальное кодовое имя материала (артикул, штрихкод и т.п.)
     */
    private String codeName;

    /**
     * Назавание материала
     */
    private String name;

    /**
     * Цена за единицу
     */
    private BigDecimal price;

    /**
     * Текцщий остаток
     */
    private BigDecimal remains;

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
}
