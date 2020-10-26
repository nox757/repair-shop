package ru.chibisov.service.dto.mapper;

import org.mapstruct.Mapper;
import ru.chibisov.model.Material;
import ru.chibisov.model.RequestMaterial;
import ru.chibisov.service.dto.MaterialDto;
import ru.chibisov.service.dto.RequestMaterialDto;

import java.util.List;

@Mapper
public interface RequestMaterialMapper {

    RequestMaterialDto map(RequestMaterial supplier);

    RequestMaterial map(RequestMaterialDto supplierDto);

    List<RequestMaterialDto> map(List<RequestMaterial> suppliers);

}
