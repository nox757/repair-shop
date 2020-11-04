package ru.chibisov.controller.dto.mapper;

import org.mapstruct.Mapper;
import ru.chibisov.controller.dto.MaterialDto;
import ru.chibisov.model.Material;

import java.util.List;

@Mapper
public interface MaterialMapper {

    MaterialDto map(Material supplier);

    Material map(MaterialDto supplierDto);

    List<MaterialDto> map(List<Material> suppliers);

}
