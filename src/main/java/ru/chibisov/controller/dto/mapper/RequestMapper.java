package ru.chibisov.controller.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.chibisov.controller.dto.RequestDto;
import ru.chibisov.model.Request;

import java.util.List;

@Mapper(uses = {RequestMaterialMapper.class})
public interface RequestMapper {

    @Mapping(target = "repairerId", source = "repairer.id")
    @Mapping(target = "nameRepairer", source = "repairer.name")
    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "nameCustomer", source = "customer.name")
    RequestDto map(Request request);

    @Mapping(target = "repairer.id", source = "repairerId")
    @Mapping(target = "repairer.name", source = "nameRepairer")
    @Mapping(target = "customer.id", source = "customerId")
    @Mapping(target = "customer.name", source = "nameCustomer")
    Request map(RequestDto requestDto);

    List<RequestDto> map(List<Request> requests);


}
