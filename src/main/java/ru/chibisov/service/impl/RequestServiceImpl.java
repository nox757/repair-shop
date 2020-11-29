package ru.chibisov.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.chibisov.controller.dto.RequestDto;
import ru.chibisov.controller.dto.RequestMaterialDto;
import ru.chibisov.controller.dto.mapper.RequestMapper;
import ru.chibisov.controller.dto.mapper.RequestMaterialMapper;
import ru.chibisov.dao.MaterialDao;
import ru.chibisov.dao.RequestDao;
import ru.chibisov.dao.RequestMaterialDao;
import ru.chibisov.dao.UserDao;
import ru.chibisov.exception.BadDataFieldException;
import ru.chibisov.exception.ObjectNotFoundException;
import ru.chibisov.model.Material;
import ru.chibisov.model.Request;
import ru.chibisov.model.RequestMaterial;
import ru.chibisov.model.User;
import ru.chibisov.service.RequestService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RequestServiceImpl implements RequestService {

    private static final Logger log = LogManager.getLogger(RequestServiceImpl.class.getName());

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
        setUserByIdToRequest(request);
        Request result = requestDao.create(request);
        return requestMapper.map(updateRequestMaterial(request, result));
    }

    @Override
    public RequestDto updateRequest(RequestDto requestDto) {
        Request request = requestMapper.map(requestDto);
        Request result = requestDao.update(request);
        return requestMapper.map(updateRequestMaterial(request, result));
    }

    @Override
    public void removeRequestById(Long id) {
        if (requestDao.deleteById(id) == null) {
            throw new ObjectNotFoundException(String.valueOf(id));
        }
    }

    @Override
    public List<RequestDto> getAllRequests() {
        List<Request> requests = new ArrayList<>(requestDao.getAll());
        return requestMapper.map(requests);
    }

    @Override
    public List<RequestDto> addMaterials(List<RequestMaterialDto> materialDtos) {
        return null;
    }


    /**
     * Устанавливает актуальные сущности мастера и заказчика в заявке
     * по полученным Id
     *
     * @param request
     */
    private void setUserByIdToRequest(Request request) {
        User repairer = request.getRepairer();
        User customer = request.getCustomer();
        if (repairer != null && repairer.getId() != null) {
            User user = userDao.getById(repairer.getId());
            if (user == null) {
                throw new BadDataFieldException(String.format("Not found user with id %s", repairer.getId()));
            }
            request.setRepairer(user);
        }
        if (customer != null) {
            User user = userDao.getById(customer.getId());
            if (user == null) {
                throw new BadDataFieldException(String.format("Not found user with id %s", customer.getId()));
            }
            request.setCustomer(user);
        }
    }

    /**
     * Обновляет информацию по используемым матералам и их количеству
     * для выполнения заявки
     * Информация по материалу ищется по ИД
     *
     * @param request
     * @param result
     * @return
     */
    private Request updateRequestMaterial(Request request, Request result) {
        if (request.getMaterials() != null && !request.getMaterials().isEmpty()) {
            Set<RequestMaterial> requestMaterialSet = new HashSet<>(request.getMaterials());
            for (RequestMaterial requestMaterial : requestMaterialSet) {
                requestMaterial.setRequest(result);
                Long idMaterial = requestMaterial.getMaterial().getId();
                Material material = materialDao.getById(idMaterial);
                if (material == null) {
                    throw new BadDataFieldException(String.format("Not found material with %s", idMaterial));
                }
                requestMaterial.setMaterial(material);
            }
            result.setMaterials((Set<RequestMaterial>) requestMaterialDao.addAll(requestMaterialSet));
        }
        return result;
    }

}
