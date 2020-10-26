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
import ru.chibisov.service.dto.RequestDto;
import ru.chibisov.service.dto.mapper.RequestMapper;

import java.util.HashSet;
import java.util.Set;

@Service
public class RequestServiceImpl implements RequestService {

    private static final Logger log = LogManager.getLogger(RequestServiceImpl.class.getName());

    private RequestDao requestDao;
    private RequestMaterialDao requestMaterialDao;
    private RequestMapper mapper;


    @Autowired
    public RequestServiceImpl(RequestDao requestDao,
                              RequestMaterialDao requestMaterialDao) {
        log.info("createService");
        this.requestDao = requestDao;
        this.requestMaterialDao = requestMaterialDao;
    }

    @Override
    public RequestDto getRequestById(Long id) {
        Request result = requestDao.getById(id);
        result.setMaterials(requestMaterialDao.getByRequestId(id));
        return mapper.map(result);
    }

    @Override
    public RequestDto addRequest(RequestDto requestDto) {
        Request request = mapper.map(requestDto);
        Request result =  requestDao.create(request);
        return mapper.map(updateRequestMaterial(request, result));
    }

    @Override
    public RequestDto updateRequest(RequestDto requestDto) {
        Request request = mapper.map(requestDto);
        Request result =  requestDao.update(request);
        return mapper.map(updateRequestMaterial(request, result));
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
