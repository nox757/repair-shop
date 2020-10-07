package ru.chibisov.model;

import java.math.BigDecimal;

public class Material implements Identifiable<Long> {

    private static final long serialVersionUID = 771612548606857971L;

    private Long id;
    private String codeName;
    private String name;
    private BigDecimal price;
    private BigDecimal remains;
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
        if (o == null || getClass() != o.getClass()) return false;

        Material material = (Material) o;

        if (!id.equals(material.id)) return false;
        return codeName.equals(material.codeName);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + codeName.hashCode();
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
                ", supplier=" + supplier +
                '}';
    }
}