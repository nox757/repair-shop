package ru.chibisov.service;

import ru.chibisov.controller.dto.SupplierDto;

import java.util.List;

public interface SupplierService {

    SupplierDto getSupplierById(Long id);

    SupplierDto addSupplier(SupplierDto supplier);

    SupplierDto updateSupplier(SupplierDto supplier);

    SupplierDto removeSupplier(SupplierDto supplier);

    List<SupplierDto> getAllSuppliers();
}
