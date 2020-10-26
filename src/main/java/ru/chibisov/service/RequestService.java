package ru.chibisov.service;

import ru.chibisov.model.Request;
import ru.chibisov.service.dto.RequestDto;

public interface RequestService {

    RequestDto getRequestById(Long id);

    RequestDto addRequest(RequestDto request);

    RequestDto updateRequest(RequestDto request);

}
