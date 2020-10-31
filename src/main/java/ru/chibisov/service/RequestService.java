package ru.chibisov.service;

import ru.chibisov.controller.dto.RequestDto;
import ru.chibisov.controller.dto.RequestMaterialDto;
import ru.chibisov.model.Request;

import java.util.List;

public interface RequestService {

    RequestDto getRequestById(Long id);

    RequestDto addRequest(RequestDto requestDto);

    RequestDto updateRequest(RequestDto requestDto);

    RequestDto removeRequest(RequestDto requestDto);

    List<RequestDto> getAllRequests();

    /**
     *
     * @return обновленную заявку с прикрепленнымы к ней материалами
     */
    List<RequestDto> addMaterials(List<RequestMaterialDto> materialDtos);

}
