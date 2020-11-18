package ru.chibisov.dao.impl;

import org.springframework.stereotype.Repository;
import ru.chibisov.dao.RequestMaterialDao;
import ru.chibisov.model.RequestMaterial;

import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class RequestMaterialDaoImpl extends AbstractDao<RequestMaterial, Long> implements RequestMaterialDao {

    public RequestMaterialDaoImpl() {
        super(RequestMaterial.class, new HashMap<>());
    }

    @Override
    public Set<RequestMaterial> getByRequestId(Long requestId) {
        return elements.values()
                .stream()
                .filter(requestMaterial -> requestMaterial.getContractId().equals(requestId))
                .collect(Collectors.toSet());
    }
}
