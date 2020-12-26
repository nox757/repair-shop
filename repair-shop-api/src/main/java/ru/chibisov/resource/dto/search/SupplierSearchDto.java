package ru.chibisov.resource.dto.search;

public class SupplierSearchDto {

    /**
     * Название организации поставщика
     */
    private String orgName;

    /**
     * ФИО менеджера
     */
    private String nameAgent;

    /**
     * Контактный телефон медежера отдела продаж
     */
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
