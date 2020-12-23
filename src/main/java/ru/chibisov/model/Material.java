package ru.chibisov.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Представление сущности материала в системе
 * Используется в заявке
 */
@Entity
@Table(name = "material")
public class Material extends CreateAtIdentified implements Identifiable<Long> {

    private static final long serialVersionUID = 771612548606857971L;

    /**
     * Идентификатор материаала в системе
     */
    @Id
    @Column(name = "material_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "material_seq_gen")
    @SequenceGenerator(name = "material_seq_gen", sequenceName = "material_material_id_seq", allocationSize = 1)
    private Long id;

    /**
     * Уникальное кодовое имя материала (артикул, штрихкод и т.п.)
     */
    @Column(name = "code_name")
    private String codeName;

    /**
     * Назавание материала
     */
    @Column
    private String name;

    /**
     * Цена за единицу
     */
    @Column
    private BigDecimal price;

    /**
     * Текцщий остаток
     */
    @Column
    private BigDecimal remains;

    /**
     * Идентификатор поставщика в системе
     */
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getRemains() {
        return remains;
    }

    public void setRemains(BigDecimal remains) {
        this.remains = remains;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Material)) return false;

        Material material = (Material) o;

        if (!Objects.equals(codeName, material.codeName)) return false;
        return Objects.equals(supplier, material.supplier);
    }

    @Override
    public int hashCode() {
        int result = codeName != null ? codeName.hashCode() : 0;
        result = 31 * result + (supplier != null ? supplier.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", codeName='" + codeName + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", remains=" + remains +
                '}';
    }
}
