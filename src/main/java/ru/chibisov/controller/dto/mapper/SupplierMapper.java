package ru.chibisov.controller.dto.mapper;

import org.mapstruct.Mapper;
import ru.chibisov.model.Supplier;
import ru.chibisov.controller.dto.SupplierDto;

import java.util.List;

@Mapper
public interface SupplierMapper {

    SupplierDto map(Supplier supplier);

    Supplier map(SupplierDto supplierDto);

    List<SupplierDto> map(List<Supplier> suppliers);

}
