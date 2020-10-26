package ru.chibisov.service.dto.mapper;

import org.mapstruct.Mapper;
import ru.chibisov.model.Request;
import ru.chibisov.service.dto.RequestDto;

import java.util.List;

@Mapper
public interface RequestMapper {

    RequestDto map(Request supplier);

    Request map(RequestDto supplierDto);

    List<RequestDto> map(List<Request> suppliers);

}
