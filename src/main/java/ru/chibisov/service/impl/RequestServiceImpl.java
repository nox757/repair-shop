package ru.chibisov.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.chibisov.dao.RequestDao;
import ru.chibisov.dao.RequestMaterialDao;
import ru.chibisov.model.Request;
import ru.chibisov.model.RequestMaterial;
import ru.chibisov.service.RequestService;

import java.util.HashSet;
import java.util.Set;

@Service
public class RequestServiceImpl implements RequestService {

    private static final Logger log = LogManager.getLogger(RequestServiceImpl.class.getName());

    private RequestDao requestDao;
    private RequestMaterialDao requestMaterialDao;

    @Autowired
    public RequestServiceImpl(RequestDao requestDao, RequestMaterialDao requestMaterialDao) {
        log.info("createService");
        this.requestDao = requestDao;
        this.requestMaterialDao = requestMaterialDao;
    }

    @Override
    public Request getRequestById(Long id) {
        Request result = requestDao.getById(id);
        result.setMaterials(requestMaterialDao.getByRequestId(id));
        return result;
    }

    @Override
    public Request addRequest(Request request) {
        Request result = requestDao.create(request);
        return updateRequestMaterial(request, result);
    }

    @Override
    public Request updateRequest(Request request) {
        Request result = requestDao.update(request);
        return updateRequestMaterial(request, result);
    }

    private Request updateRequestMaterial(Request request, Request result) {
        if(!request.getMaterials().isEmpty()) {
            Set<RequestMaterial> requestMaterialSet = new HashSet<>(request.getMaterials());
            for (RequestMaterial requestMaterial : requestMaterialSet) {
                requestMaterial.setContractId(result.getId());
            }
            result.setMaterials((Set<RequestMaterial>) requestMaterialDao.addAll(requestMaterialSet));
        }
        return result;
    }
}
