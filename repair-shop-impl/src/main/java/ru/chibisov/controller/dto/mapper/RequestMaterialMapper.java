package ru.chibisov.controller.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.chibisov.resource.dto.RequestMaterialDto;
import ru.chibisov.model.RequestMaterial;

import java.util.List;

@Mapper
public interface RequestMaterialMapper {

    @Mapping(target = "requestId", source = "id.requestId")
    @Mapping(target = "materialId", source = "id.materialId")
    RequestMaterialDto map(RequestMaterial requestMaterial);

    @Mapping(target = "id.materialId", source = "materialId")
    @Mapping(target = "id.requestId", source = "requestId")
    RequestMaterial map(RequestMaterialDto requestMaterialDto);

    List<RequestMaterialDto> map(List<RequestMaterial> requestMaterials);

}
