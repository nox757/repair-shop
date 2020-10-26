package ru.chibisov.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.chibisov.dao.MaterialDao;
import ru.chibisov.dao.impl.MaterialDaoImpl;
import ru.chibisov.model.Material;
import ru.chibisov.service.MaterialService;
import ru.chibisov.service.dto.MaterialDto;
import ru.chibisov.service.dto.mapper.MaterialMapper;

import java.math.BigDecimal;

@Service
public class MaterialServiceImpl implements MaterialService {

    private static final Logger log = LogManager.getLogger(MaterialServiceImpl.class.getName());

    private MaterialDao materialDao;
    private MaterialMapper mapper;

    @Autowired
    public MaterialServiceImpl(MaterialDao materialDao, MaterialMapper mapper) {
        log.info("createService");
        this.materialDao = materialDao;
        this.mapper = mapper;
    }

    @Override
    public MaterialDto getMaterialById(Long id) {
        return mapper.map(materialDao.getById(id));
    }

    @Override
    public MaterialDto addMaterial(MaterialDto materialDto) {
        Material material = mapper.map(materialDto);
        return mapper.map(materialDao.create(material));
    }

    @Override
    public MaterialDto updateMaterial(MaterialDto materialDto) {
        Material material = mapper.map(materialDto);
        return mapper.map(materialDao.update(material));
    }

    @Override
    public MaterialDto removeMaterial(MaterialDto materialDto) {
        Material material = mapper.map(materialDto);
        return mapper.map(materialDao.delete(material));
    }

    @Override
    public MaterialDto updateRemains(String codeName, BigDecimal value) {
        return mapper.map(materialDao.updateRemains(codeName, value));
    }


}
