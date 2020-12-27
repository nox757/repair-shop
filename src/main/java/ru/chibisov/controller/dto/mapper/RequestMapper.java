package ru.chibisov.controller.dto.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.chibisov.controller.dto.RequestDto;
import ru.chibisov.model.Request;
import ru.chibisov.model.User;

import java.util.List;

@Mapper(uses = {RequestMaterialMapper.class})
public interface RequestMapper {

    @Mapping(target = "repairerId", source = "repairer.id")
    @Mapping(target = "nameRepairer", source = "repairer.name")
    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "nameCustomer", source = "customer.name")
    RequestDto map(Request request);

    @Mapping(target = "repairer", ignore = true)
    @Mapping(target = "customer", ignore = true)
    Request map(RequestDto requestDto);

    List<RequestDto> map(List<Request> requests);

    @AfterMapping
    default void userToRequest(RequestDto dto, @MappingTarget Request request) {
        request.setCustomer(convertToUser(dto.getCustomerId(), dto.getNameCustomer()));
        request.setRepairer(convertToUser(dto.getRepairerId(), dto.getNameRepairer()));
    }

    default User convertToUser(Long id, String name) {
        if(id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        if(name != null) {
            user.setName(name);
        }
        return user;
    }


}
