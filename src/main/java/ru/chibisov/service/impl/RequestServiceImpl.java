package ru.chibisov.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chibisov.controller.dto.RequestDto;
import ru.chibisov.controller.dto.mapper.RequestMapper;
import ru.chibisov.controller.dto.mapper.RequestMaterialMapper;
import ru.chibisov.dao.MaterialDao;
import ru.chibisov.dao.RequestDao;
import ru.chibisov.dao.RequestMaterialDao;
import ru.chibisov.dao.UserDao;
import ru.chibisov.exception.ObjectNotFoundException;
import ru.chibisov.model.Request;
import ru.chibisov.model.RequestMaterial;
import ru.chibisov.model.RequestMaterialPk;
import ru.chibisov.service.RequestService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RequestServiceImpl implements RequestService {

    private static final Logger log = LoggerFactory.getLogger(RequestServiceImpl.class.getName());

    private RequestDao requestDao;
    private UserDao userDao;
    private RequestMaterialDao requestMaterialDao;
    private MaterialDao materialDao;
    private RequestMapper requestMapper;
    private RequestMaterialMapper materialMapper;

    public RequestServiceImpl(RequestDao requestDao,
                              UserDao userDao,
                              RequestMaterialDao requestMaterialDao,
                              MaterialDao materialDao,
                              RequestMapper requestMapper,
                              RequestMaterialMapper materialMapper) {
        log.info("createService");
        this.requestDao = requestDao;
        this.userDao = userDao;
        this.requestMaterialDao = requestMaterialDao;
        this.materialDao = materialDao;
        this.requestMapper = requestMapper;
        this.materialMapper = materialMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public RequestDto getRequestById(Long id) {
        Request result = requestDao.getById(id);
        if (result == null) {
            throw new ObjectNotFoundException(String.valueOf(id));
        }
        result.setMaterials(requestMaterialDao.getByRequestId(id));
        return requestMapper.map(result);
    }

    @Override
    public RequestDto addRequest(RequestDto requestDto) {
        Request request = requestMapper.map(requestDto);
        Request result = requestDao.create(request);
        addRequestMaterial(request, result);
        return requestMapper.map(result);
    }

    @Override
    public RequestDto updateRequest(RequestDto requestDto) {
        Request request = requestMapper.map(requestDto);
        Request result = requestDao.update(request);
        Set<RequestMaterial> requestMaterialSet = new HashSet<>(request.getMaterials());
        result.setMaterials(requestMaterialDao.addOrUpdateAll(requestMaterialSet));
        return requestMapper.map(result);
    }

    @Override
    public void removeRequestById(Long id) {
        requestMaterialDao.deleteByRequestId(id);
        if (requestDao.deleteById(id) == null) {
            throw new ObjectNotFoundException(String.valueOf(id));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<RequestDto> getAllRequests() {
        List<Request> requests = new ArrayList<>(requestDao.getAll());
        for (Request request : requests) {
            request.setMaterials(requestMaterialDao.getByRequestId(request.getId()));
        }
        return requestMapper.map(requests);
    }

    /**
     * Создает информацию по используемым матералам и их количеству
     * для выполнения заявки
     *
     * @param request
     * @param result
     */
    private void addRequestMaterial(final Request request, Request result) {
        if (request.getMaterials() != null && !request.getMaterials().isEmpty()) {
            Set<RequestMaterial> requestMaterialSet = new HashSet<>(request.getMaterials());
            for (RequestMaterial requestMaterial : requestMaterialSet) {
                requestMaterial.setId(new RequestMaterialPk(requestMaterial.getId().getMaterialId(), result.getId()));
            }
            result.setMaterials((Set<RequestMaterial>) requestMaterialDao.addAll(requestMaterialSet));
        }
    }

}
