package ru.chibisov.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.chibisov.dao.MaterialDao;
import ru.chibisov.dao.impl.MaterialDaoImpl;
import ru.chibisov.model.Material;
import ru.chibisov.service.MaterialService;

import java.math.BigDecimal;

public class MaterialServiceImpl implements MaterialService {

    private static final Logger log = LogManager.getLogger(MaterialServiceImpl.class.getName());

    private MaterialDao materialDao;

    public MaterialServiceImpl() {
        log.info("createService");
    }

    public void setMaterialDao(MaterialDaoImpl materialDao) {
        this.materialDao = materialDao;
    }

    @Override
    public Material getMaterialById(Long id) {
        return materialDao.getById(id);
    }

    @Override
    public Material addMaterial(Material material) {
        return materialDao.create(material);
    }

    @Override
    public Material updateMaterial(Material material) {
        return materialDao.update(material);
    }

    @Override
    public Material updateRemains(String codeName, BigDecimal value) {
        return materialDao.updateRemains(codeName, value);
    }

    @Override
    public Material removeMaterial(Material material) {
        return materialDao.delete(material);
    }


}
