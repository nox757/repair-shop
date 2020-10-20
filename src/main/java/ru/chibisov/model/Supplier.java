package ru.chibisov.model;

import java.util.Set;

/**
 * Представление сущности поставщик в системе
 */
public class Supplier implements Identifiable<Long> {

    private static final long serialVersionUID = -7077314038055460239L;

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
    private Set<Material> materials;

    public Supplier() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getNameAgent() {
        return nameAgent;
    }

    public void setNameAgent(String nameAgent) {
        this.nameAgent = nameAgent;
    }

    public String getPhoneAgent() {
        return phoneAgent;
    }

    public void setPhoneAgent(String phoneAgent) {
        this.phoneAgent = phoneAgent;
    }

    public Set<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(Set<Material> materials) {
        this.materials = materials;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", orgName='" + orgName + '\'' +
                ", nameAgent='" + nameAgent + '\'' +
                ", phoneAgent='" + phoneAgent + '\'' +
                ", materials=" + materials +
                '}';
    }
}
