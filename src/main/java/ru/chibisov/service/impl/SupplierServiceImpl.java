package ru.chibisov.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.chibisov.dao.SupplierDao;
import ru.chibisov.model.Supplier;
import ru.chibisov.service.SupplierService;
import ru.chibisov.service.dto.SupplierDto;
import ru.chibisov.service.dto.mapper.SupplierMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    private static final Logger log = LogManager.getLogger(SupplierServiceImpl.class.getName());

    private SupplierDao supplierDao;
    private SupplierMapper mapper;

    @Autowired
    public SupplierServiceImpl(SupplierDao supplierDao, SupplierMapper mapper) {
        log.info("createService");
        this.supplierDao = supplierDao;
        this.mapper = mapper;
    }

    @Override
    public SupplierDto getSupplierById(Long id) {
        return mapper.map(supplierDao.getById(id));
    }

    @Override
    public SupplierDto addSupplier(SupplierDto supplierDto) {
        Supplier supplier = mapper.map(supplierDto);
        return mapper.map(supplierDao.create(supplier));
    }

    @Override
    public SupplierDto updateSupplier(SupplierDto supplierDto) {
        Supplier supplier = mapper.map(supplierDto);
        return mapper.map(supplierDao.update(supplier));
    }

    @Override
    public SupplierDto deleteSupplier(SupplierDto supplierDto) {
        Supplier supplier = mapper.map(supplierDto);
        return mapper.map(supplierDao.delete(supplier));
    }

    @Override
    public List<SupplierDto> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>(supplierDao.getAll());
        return mapper.map(suppliers);
    }
}
