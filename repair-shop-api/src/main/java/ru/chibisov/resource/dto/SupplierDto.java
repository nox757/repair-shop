package ru.chibisov.resource.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Модель поставщика")
public class SupplierDto {

    @ApiModelProperty(value = "Идентификатор поставщика", example = "2")
    private Long id;

    @ApiModelProperty(value = "Название организации поставщика", example = "ООО 'Рога и копыта'", required = true)
    private String orgName;

    @ApiModelProperty(value = "ФИО менеджера", example = "Костин Константин Константинович", required = true)
    private String nameAgent;

    @ApiModelProperty(value = "Контактный телефон менеджера отдела продаж", example = "+79770011223", required = true)
    private String phoneAgent;

    public Long getId() {
        return id;
    }

    public SupplierDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getOrgName() {
        return orgName;
    }

    public SupplierDto setOrgName(String orgName) {
        this.orgName = orgName;
        return this;
    }

    public String getNameAgent() {
        return nameAgent;
    }

    public SupplierDto setNameAgent(String nameAgent) {
        this.nameAgent = nameAgent;
        return this;
    }

    public String getPhoneAgent() {
        return phoneAgent;
    }

    public SupplierDto setPhoneAgent(String phoneAgent) {
        this.phoneAgent = phoneAgent;
        return this;
    }

    @Override
    public String toString() {
        return "SupplierDto{" +
                "id=" + id +
                ", orgName='" + orgName + '\'' +
                ", nameAgent='" + nameAgent + '\'' +
                ", phoneAgent='" + phoneAgent + '\'' +
                '}';
    }
}
