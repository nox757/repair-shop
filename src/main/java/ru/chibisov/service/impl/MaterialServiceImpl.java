package ru.chibisov.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chibisov.controller.dto.MaterialDto;
import ru.chibisov.controller.dto.mapper.MaterialMapper;
import ru.chibisov.exception.ObjectNotFoundException;
import ru.chibisov.model.Material;
import ru.chibisov.repository.MaterialRepository;
import ru.chibisov.service.MaterialService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MaterialServiceImpl implements MaterialService {

    private static final Logger log = LoggerFactory.getLogger(MaterialServiceImpl.class.getName());

    private MaterialRepository materialRepository;
    private MaterialMapper mapper;

    public MaterialServiceImpl(MaterialRepository materialRepository, MaterialMapper mapper) {
        log.info("saveService");
        this.materialRepository = materialRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public MaterialDto getMaterialById(Long id) {
        Material material = materialRepository.getOne(id);
        if (material.getId() == null) {
            throw new ObjectNotFoundException(String.valueOf(id));
        }
        return mapper.map(material);
    }

    @Override
    public MaterialDto addMaterial(MaterialDto materialDto) {
        Material material = mapper.map(materialDto);
        return mapper.map(materialRepository.save(material));
    }

    @Override
    public MaterialDto updateMaterial(MaterialDto materialDto) {
        Material material = mapper.map(materialDto);
        return mapper.map(materialRepository.save(material));
    }

    @Override
    public void removeMaterialById(Long id) {
        try {
            materialRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.debug(e.toString());
            throw new ObjectNotFoundException(String.valueOf(id));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaterialDto> getAllMaterials() {
        List<Material> materials = new ArrayList<>(materialRepository.findAll());
        return mapper.map(materials);
    }

    @Override
    public List<MaterialDto> getAllMaterialsBySupplierId(Long supplierId) {
        List<Material> materials = new ArrayList<>(materialRepository.findAllBySupplierId(supplierId));
        return mapper.map(materials);
    }
}
