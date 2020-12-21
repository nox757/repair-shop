package ru.chibisov.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chibisov.controller.dto.SupplierDto;
import ru.chibisov.controller.dto.mapper.MaterialMapper;
import ru.chibisov.controller.dto.mapper.SupplierMapper;
import ru.chibisov.dao.MaterialDao;
import ru.chibisov.dao.SupplierDao;
import ru.chibisov.exception.ObjectNotFoundException;
import ru.chibisov.model.Material;
import ru.chibisov.model.Supplier;
import ru.chibisov.service.SupplierService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    private static final Logger log = LogManager.getLogger(SupplierServiceImpl.class.getName());

    private SupplierDao supplierDao;
    private SupplierMapper mapper;

    private MaterialDao materialDao;
    private MaterialMapper materialMapper;

    public SupplierServiceImpl(SupplierDao supplierDao, SupplierMapper mapper,
                               MaterialDao materialDao, MaterialMapper materialMapper) {
        log.info("createService");
        this.supplierDao = supplierDao;
        this.mapper = mapper;
        this.materialDao = materialDao;
        this.materialMapper = materialMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierDto getSupplierById(Long id) {
        Supplier supplier = supplierDao.getById(id);
        if (supplier == null) {
            throw new ObjectNotFoundException(String.valueOf(id));
        }
        supplier.setMaterials(new HashSet<>(materialDao.getBySupplierId(id)));
        return mapper.map(supplier);
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
    public void removeSupplierById(Long id) {
        if (supplierDao.deleteById(id) == null) {
            throw new ObjectNotFoundException(String.valueOf(id));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupplierDto> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>(supplierDao.getAll());
        return mapper.map(suppliers);
    }

}
