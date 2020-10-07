package ru.chibisov.service;

import ru.chibisov.model.Supplier;

public interface SupplierService {

    Supplier getSupplierById(Long id);

    Supplier addSupplier(Supplier supplier);

    Supplier updateSupplier(Supplier supplier);

    Supplier removeSupplier(Supplier supplier);
}
