package ru.chibisov.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.chibisov.dao.SupplierDao;
import ru.chibisov.dao.impl.SupplierDaoImpl;
import ru.chibisov.model.Supplier;
import ru.chibisov.service.SupplierService;

public class SupplierServiceImpl implements SupplierService {

    private static final Logger log = LogManager.getLogger(SupplierServiceImpl.class.getName());

    private SupplierDao supplierDao;

    public SupplierServiceImpl() {
        log.info("createService");
    }

    public void setSupplierDao(SupplierDaoImpl supplierDao) {
        this.supplierDao = supplierDao;
    }

    @Override
    public Supplier getSupplierById(Long id) {
        return supplierDao.getById(id);
    }

    @Override
    public Supplier addSupplier(Supplier supplier) {
        return supplierDao.create(supplier);
    }

    @Override
    public Supplier updateSupplier(Supplier supplier) {
        return supplierDao.update(supplier);
    }

    @Override
    public Supplier removeSupplier(Supplier supplier) {
        return supplierDao.delete(supplier);
    }

}
