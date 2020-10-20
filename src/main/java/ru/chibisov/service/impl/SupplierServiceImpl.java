package ru.chibisov.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.chibisov.dao.SupplierDao;
import ru.chibisov.dao.impl.SupplierDaoImpl;
import ru.chibisov.model.Supplier;
import ru.chibisov.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {

    private static final Logger log = LogManager.getLogger(SupplierServiceImpl.class.getName());

    private SupplierDao supplierDao;

    public SupplierServiceImpl() {
        log.info("createService");
    }

    @Autowired
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
