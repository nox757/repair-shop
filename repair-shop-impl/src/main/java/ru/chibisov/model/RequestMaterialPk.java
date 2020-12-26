package ru.chibisov.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RequestMaterialPk implements Serializable {

    @Column(name = "material_id")
    protected Long materialId;

    @Column(name = "request_id")
    protected Long requestId;

    public RequestMaterialPk() {

    }

    public RequestMaterialPk(Long materialId, Long requestId) {
        this.materialId = materialId;
        this.requestId = requestId;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public Long getRequestId() {
        return requestId;
    }

    public RequestMaterialPk setMaterialId(Long materialId) {
        this.materialId = materialId;
        return this;
    }

    public RequestMaterialPk setRequestId(Long requestId) {
        this.requestId = requestId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequestMaterialPk that = (RequestMaterialPk) o;

        if (!Objects.equals(materialId, that.materialId)) return false;
        return Objects.equals(requestId, that.requestId);
    }

    @Override
    public int hashCode() {
        int result = materialId != null ? materialId.hashCode() : 0;
        result = 31 * result + (requestId != null ? requestId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RequestMaterialPk{" +
                "materialId=" + materialId +
                ", requestId=" + requestId +
                '}';
    }
}
