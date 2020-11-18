package ru.chibisov.dao.impl;

import org.springframework.stereotype.Repository;
import ru.chibisov.dao.RequestDao;
import ru.chibisov.model.Request;

import java.util.HashMap;

@Repository
public class RequestDaoImpl extends AbstractDao<Request, Long> implements RequestDao {

    public RequestDaoImpl() {
        super(Request.class, new HashMap<>());
    }
}
