package ru.chibisov.controller.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.chibisov.resource.dto.MaterialDto;
import ru.chibisov.model.Material;

import java.util.List;

@Mapper(uses = {SupplierMapper.class})
public interface MaterialMapper {

    @Mapping(source = "supplier.id", target = "supplierId")
    MaterialDto map(Material material);

    @Mapping(source = "supplierId", target = "supplier.id")
    Material map(MaterialDto materialDto);

    List<MaterialDto> map(List<Material> materials);

}
