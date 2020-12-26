package ru.chibisov.resource.dto.search;

import java.math.BigDecimal;

public class MaterialSearchDto {

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
     * Назавание организации поставщика
     */
    private String orgName;

    public String getCodeName() {
        return codeName;
    }

    public MaterialSearchDto setCodeName(String codeName) {
        this.codeName = codeName;
        return this;
    }

    public String getName() {
        return name;
    }

    public MaterialSearchDto setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public MaterialSearchDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getOrgName() {
        return orgName;
    }

    public MaterialSearchDto setOrgName(String orgName) {
        this.orgName = orgName;
        return this;
    }
}
