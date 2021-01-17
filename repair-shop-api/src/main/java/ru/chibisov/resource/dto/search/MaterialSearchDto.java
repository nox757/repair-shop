package ru.chibisov.resource.dto.search;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(description = "Модель для поиска материалов")
public class MaterialSearchDto {

    @ApiModelProperty(value = "Уникальное кодовое имя материала", example = "100/225-54/5")
    private String codeName;

    @ApiModelProperty(value = "Название материала материала", example = "Сталь")
    private String name;

    @ApiModelProperty(value = "Цена за единицу", example = "12.12")
    private BigDecimal price;

    @ApiModelProperty(value = "Название организации поставщика", example = "ООО Рога и копыта")
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

    @Override
    public String toString() {
        return "MaterialSearchDto{" +
                "codeName='" + codeName + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", orgName='" + orgName + '\'' +
                '}';
    }
}
