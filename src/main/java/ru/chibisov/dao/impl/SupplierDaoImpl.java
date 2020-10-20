package ru.chibisov.dao.impl;

import org.springframework.stereotype.Repository;
import ru.chibisov.dao.SupplierDao;
import ru.chibisov.model.Supplier;

import java.util.HashMap;

@Repository
public class SupplierDaoImpl extends AbstractDao<Supplier, Long> implements SupplierDao {

    public SupplierDaoImpl() {
        super(Supplier.class, new HashMap<>());
    }
}
