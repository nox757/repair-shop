package ru.chibisov.service.dto.mapper;

import org.mapstruct.Mapper;
import ru.chibisov.model.Material;
import ru.chibisov.service.dto.MaterialDto;

import java.util.List;

@Mapper
public interface MaterialMapper {

    MaterialDto map(Material supplier);

    Material map(MaterialDto supplierDto);

    List<MaterialDto> map(List<Material> suppliers);

}
