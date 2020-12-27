package ru.chibisov.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chibisov.controller.dto.RequestDto;
import ru.chibisov.controller.dto.mapper.RequestMapper;
import ru.chibisov.controller.dto.mapper.RequestMaterialMapper;
import ru.chibisov.exception.ObjectNotFoundException;
import ru.chibisov.model.Request;
import ru.chibisov.model.RequestMaterial;
import ru.chibisov.model.RequestMaterialPk;
import ru.chibisov.repository.RequestMaterialRepository;
import ru.chibisov.repository.RequestRepository;
import ru.chibisov.service.RequestService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RequestServiceImpl implements RequestService {

    private static final Logger log = LoggerFactory.getLogger(RequestServiceImpl.class.getName());

    private RequestRepository requestRepository;
    private RequestMaterialRepository requestMaterialRepository;
    private RequestMapper requestMapper;

    public RequestServiceImpl(RequestRepository requestRepository,
                              RequestMaterialRepository requestMaterialRepository,
                              RequestMapper requestMapper,
                              RequestMaterialMapper materialMapper) {
        log.info("saveService");
        this.requestRepository = requestRepository;
        this.requestMaterialRepository = requestMaterialRepository;
        this.requestMapper = requestMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public RequestDto getRequestById(Long id) {
        Request result = requestRepository.getOne(id);
        if (result.getId() == null) {
            throw new ObjectNotFoundException(String.valueOf(id));
        }
        result.setMaterials(requestMaterialRepository.findByRequestId(id));
        return requestMapper.map(result);
    }

    @Override
    public RequestDto addRequest(RequestDto requestDto) {
        Request request = requestMapper.map(requestDto);
        Request result = requestRepository.save(request);
        addRequestMaterial(request, result);
        return requestMapper.map(result);
    }

    @Override
    public RequestDto updateRequest(RequestDto requestDto) {
        Request request = requestMapper.map(requestDto);
        Request result = requestRepository.save(request);
        //todo: возможно есть более правильное решение
        Set<RequestMaterial> requestMaterials = requestMaterialRepository.findByRequestId(result.getId());
        requestMaterials.removeAll(result.getMaterials());
        requestMaterialRepository.deleteAll(requestMaterials);
        return requestMapper.map(result);
    }

    @Override
    public void removeRequestById(Long id) {
        try {
            requestRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.debug(e.toString());
            throw new ObjectNotFoundException(String.valueOf(id));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<RequestDto> getAllRequests() {
        List<Request> requests = new ArrayList<>(requestRepository.findAll());
        for (Request request : requests) {
            request.setMaterials(requestMaterialRepository.findByRequestId(request.getId()));
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
            result.setMaterials(new HashSet<>(requestMaterialRepository.saveAll(requestMaterialSet)));
        }
    }

}
