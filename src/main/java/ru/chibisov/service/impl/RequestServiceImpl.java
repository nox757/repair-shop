package ru.chibisov.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.chibisov.dao.RequestDao;
import ru.chibisov.model.Request;
import ru.chibisov.service.RequestService;

public class RequestServiceImpl implements RequestService {

    private static final Logger log = LogManager.getLogger(RequestServiceImpl.class.getName());

    private RequestDao requestDao;

    public RequestServiceImpl(RequestDao contractDao) {
        log.info("createService");
        this.requestDao = contractDao;
    }

    @Override
    public Request getRequestById(Long id) {
        return requestDao.getById(id);
    }

    @Override
    public Request addRequest(Request contract) {
        return requestDao.create(contract);
    }

    @Override
    public Request updateRequest(Request contract) {
        return requestDao.update(contract);
    }
}
