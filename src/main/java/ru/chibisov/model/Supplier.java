package ru.chibisov.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

/**
 * Представление сущности поставщик в системе
 */
@Entity
@Table(name = "supplier")
public class Supplier implements Identifiable<Long> {

    private static final long serialVersionUID = -7077314038055460239L;

    /**
     * Уникальный идентификатор пользователя в системе
     */
    @Id
    @Column(name = "supplier_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supplier_seq_gen")
    @SequenceGenerator(name = "supplier_seq_gen", sequenceName = "supplier_supplier_id_seq", allocationSize = 1)
    private Long id;

    /**
     * Название организации поставщика
     */
    @Column(name = "org_name")
    private String orgName;

    /**
     * ФИО менеджера
     */
    @Column(name = "name_agent")
    private String nameAgent;

    /**
     * Контактный телефон медежера отдела продаж
     */
    @Column(name = "phone_agent")
    private String phoneAgent;

    /**
     * Список постаавляемых материалов
     */
    @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY)
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Supplier)) return false;

        Supplier supplier = (Supplier) o;

        if (!Objects.equals(orgName, supplier.orgName)) return false;
        if (!Objects.equals(nameAgent, supplier.nameAgent)) return false;
        return Objects.equals(phoneAgent, supplier.phoneAgent);
    }

    @Override
    public int hashCode() {
        int result = orgName != null ? orgName.hashCode() : 0;
        result = 31 * result + (nameAgent != null ? nameAgent.hashCode() : 0);
        result = 31 * result + (phoneAgent != null ? phoneAgent.hashCode() : 0);
        return result;
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
