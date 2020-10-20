package ru.chibisov.model;

import java.util.Objects;

/**
 * Сущность материал-количество, используемый в заявке.
 */
public class RequestMaterial implements Identifiable<Long> {

    private static final long serialVersionUID = -2377172869257359548L;

    /**
     * Уникальный идентификатор записи в системе
     */
    private Long id;

    /**
     * Уникальный идентификатор заявки в системе
     */
    private Long requestId;

    /**
     * Материал, используемый в заявке
     */
    private Material material;

    /**
     * Количество материала для заявки
     */
    private Double quantity;


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContractId() {
        return requestId;
    }

    public void setContractId(Long requestId) {
        this.requestId = requestId;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequestMaterial that = (RequestMaterial) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(requestId, that.requestId)) return false;
        return Objects.equals(material, that.material);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (requestId != null ? requestId.hashCode() : 0);
        result = 31 * result + (material != null ? material.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RequestMaterial{" +
                "id=" + id +
                ", requestId=" + requestId +
                ", material=" + material +
                ", quantity=" + quantity +
                '}';
    }
}
