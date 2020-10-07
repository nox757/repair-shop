package ru.chibisov.dao.impl;

import ru.chibisov.dao.RequestDao;
import ru.chibisov.model.Request;

import java.util.HashMap;

public class RequestDaoImpl extends AbstractDao<Request, Long> implements RequestDao {

    public RequestDaoImpl() {
        super(Request.class, new HashMap<>());
    }
}
