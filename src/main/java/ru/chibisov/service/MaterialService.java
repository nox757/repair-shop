package ru.chibisov.service;

import ru.chibisov.model.Material;
import ru.chibisov.service.dto.MaterialDto;

import java.math.BigDecimal;

public interface MaterialService {

    MaterialDto getMaterialById(Long id);

    MaterialDto addMaterial(MaterialDto material);

    MaterialDto updateMaterial(MaterialDto material);

    MaterialDto updateRemains(String codeName, BigDecimal value);

    MaterialDto removeMaterial(MaterialDto material);

}
