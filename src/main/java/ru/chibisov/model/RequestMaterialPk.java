package ru.chibisov.model;

import java.io.Serializable;
import java.util.Objects;

public class RequestMaterialPk implements Serializable {

    protected Long materialId;
    protected Long requestId;

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
