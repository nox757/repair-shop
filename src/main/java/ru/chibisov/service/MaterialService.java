package ru.chibisov.service;

import ru.chibisov.model.Material;

import java.math.BigDecimal;

public interface MaterialService {

    Material getMaterialById(Long id);

    Material addMaterial(Material material);

    Material updateMaterial(Material material);

    Material updateRemains(String codeName, BigDecimal value);

    Material removeMaterial(Material material);

}
