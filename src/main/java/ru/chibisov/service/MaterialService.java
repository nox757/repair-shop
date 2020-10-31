package ru.chibisov.service;

import ru.chibisov.controller.dto.MaterialDto;

import java.math.BigDecimal;
import java.util.List;

public interface MaterialService {

    MaterialDto getMaterialById(Long id);

    MaterialDto addMaterial(MaterialDto material);

    MaterialDto updateMaterial(MaterialDto material);

    MaterialDto updateRemains(String codeName, BigDecimal value);

    MaterialDto removeMaterial(MaterialDto material);

    List<MaterialDto> getAllMaterials();

}
