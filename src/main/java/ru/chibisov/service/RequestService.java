package ru.chibisov.service;

import ru.chibisov.model.Request;

public interface RequestService {

    Request getRequestById(Long id);

    Request addRequest(Request request);

    Request updateRequest(Request request);

}
