package ru.chibisov.controller.dto;

import java.util.Set;

public class SupplierDto {

    /**
     * Уникальный идентификатор пользователя в системе
     */
    private Long id;

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

    /**
     * Список постаавляемых материалов
     */
    private Set<MaterialDto> materials;

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

    public Set<MaterialDto> getMaterials() {
        return materials;
    }

    public SupplierDto setMaterials(Set<MaterialDto> materials) {
        this.materials = materials;
        return this;
    }
}
