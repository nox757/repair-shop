package ru.chibisov.controller.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.chibisov.model.RequestMaterial;
import ru.chibisov.controller.dto.RequestMaterialDto;

import java.util.List;

@Mapper
public interface RequestMaterialMapper {

    @Mapping(target = "requestId", source = "request.id")
    @Mapping(target = "materialId", source = "material.id")
    RequestMaterialDto map(RequestMaterial requestMaterial);

    @Mapping(target = "request.id", source = "requestId")
    @Mapping(target = "material.id", source = "materialId")
    RequestMaterial map(RequestMaterialDto requestMaterialDto);

    List<RequestMaterialDto> map(List<RequestMaterial> requestMaterials);

}
