package ru.chibisov.service;

import ru.chibisov.model.Supplier;
import ru.chibisov.service.dto.SupplierDto;

import java.util.List;

public interface SupplierService {

    SupplierDto getSupplierById(Long id);

    SupplierDto addSupplier(SupplierDto supplier);

    SupplierDto updateSupplier(SupplierDto supplier);

    SupplierDto deleteSupplier(SupplierDto supplier);

    List<SupplierDto> getAllSuppliers();
}
