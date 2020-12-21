package ru.chibisov.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Сущность материал-количество, используемый в заявке.
 */
public class RequestMaterial implements Identifiable<RequestMaterialPk> {

    private static final long serialVersionUID = -2377172869257359548L;

    /**
     * Уникальный идентификатор записи в системе,
     * состоящий из идентификтора заявки и материала используемого в конкретной заявке
     */
    private RequestMaterialPk requestMaterialPk;

    /**
     * Количество материала для заявки
     */
    private BigDecimal quantity;

    @Override
    public RequestMaterialPk getId() {
        return requestMaterialPk;
    }

    public void setId(RequestMaterialPk requestMaterialPk) {
        this.requestMaterialPk = requestMaterialPk;
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
        if (o == null || getClass() != o.getClass()) return false;

        RequestMaterial that = (RequestMaterial) o;

        if (!Objects.equals(requestMaterialPk, that.requestMaterialPk))
            return false;
        return Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return requestMaterialPk != null ? requestMaterialPk.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "RequestMaterial{" +
                "requestMaterialPk=" + requestMaterialPk +
                ", quantity=" + quantity +
                '}';
    }
}
