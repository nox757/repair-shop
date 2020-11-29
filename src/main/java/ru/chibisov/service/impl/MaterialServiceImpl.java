package ru.chibisov.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.chibisov.controller.dto.MaterialDto;
import ru.chibisov.controller.dto.mapper.MaterialMapper;
import ru.chibisov.dao.MaterialDao;
import ru.chibisov.exception.ObjectNotFoundException;
import ru.chibisov.model.Material;
import ru.chibisov.service.MaterialService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService {

    private static final Logger log = LogManager.getLogger(MaterialServiceImpl.class.getName());

    private MaterialDao materialDao;
    private MaterialMapper mapper;

    public MaterialServiceImpl(MaterialDao materialDao, MaterialMapper mapper) {
        log.info("createService");
        this.materialDao = materialDao;
        this.mapper = mapper;
    }

    @Override
    public MaterialDto getMaterialById(Long id) {
        Material material = materialDao.getById(id);
        if (material == null) {
            throw new ObjectNotFoundException(String.valueOf(id));
        }
        return mapper.map(material);
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
    public void removeMaterialById(Long id) {
        if (materialDao.deleteById(id) == null) {
            throw new ObjectNotFoundException(String.valueOf(id));
        }
    }

    @Override
    public MaterialDto updateRemains(String codeName, BigDecimal value) {
        return mapper.map(materialDao.updateRemains(codeName, value));
    }

    @Override
    public List<MaterialDto> getAllMaterials() {
        List<Material> materials = new ArrayList<>(materialDao.getAll());
        return mapper.map(materials);
    }
}
