package ru.chibisov.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Сущность материал-количество, используемый в заявке.
 */
@Entity
@Table(name = "request_material")
public class RequestMaterial extends CreateAtIdentified implements Identifiable<RequestMaterialPk> {

    private static final long serialVersionUID = -2377172869257359548L;

    /**
     * Уникальный идентификатор записи в системе,
     * состоящий из идентификтора заявки и материала используемого в конкретной заявке
     */
    @EmbeddedId
    private RequestMaterialPk id;

    /**
     * Количество материала для заявки
     */
    @Column
    private BigDecimal quantity;

    @Override
    public RequestMaterialPk getId() {
        return id;
    }

    public void setId(RequestMaterialPk id) {
        this.id = id;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestMaterial)) return false;

        RequestMaterial that = (RequestMaterial) o;

        if (!Objects.equals(id, that.id)) return false;
        return Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RequestMaterial{" +
                "requestMaterialPk=" + id +
                ", quantity=" + quantity +
                '}';
    }
}
