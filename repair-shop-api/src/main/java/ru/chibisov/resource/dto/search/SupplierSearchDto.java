package ru.chibisov.resource.dto.search;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Модель для поиска поставщиков")
public class SupplierSearchDto {

    @ApiModelProperty(value = "Название организации поставщика", example = "ООО Рога и копыта")
    private String orgName;

    @ApiModelProperty(value = "ФИО менеджера", example = "Иванов Иван")
    private String nameAgent;

    @ApiModelProperty(value = "Контактный телефон менеджера", example = "79778800123")
    private String phoneAgent;

    public String getOrgName() {
        return orgName;
    }

    public SupplierSearchDto setOrgName(String orgName) {
        this.orgName = orgName;
        return this;
    }

    public String getNameAgent() {
        return nameAgent;
    }

    public SupplierSearchDto setNameAgent(String nameAgent) {
        this.nameAgent = nameAgent;
        return this;
    }

    public String getPhoneAgent() {
        return phoneAgent;
    }

    public SupplierSearchDto setPhoneAgent(String phoneAgent) {
        this.phoneAgent = phoneAgent;
        return this;
    }
}
