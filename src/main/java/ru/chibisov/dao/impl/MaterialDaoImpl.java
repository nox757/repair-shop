package ru.chibisov.dao.impl;

import ru.chibisov.dao.MaterialDao;
import ru.chibisov.model.Material;

import java.math.BigDecimal;
import java.util.HashMap;

public class MaterialDaoImpl extends AbstractDao<Material, Long> implements MaterialDao {

    public MaterialDaoImpl() {
        super(Material.class, new HashMap<>());
    }

    @Override
    public Material updateRemains(String codeName, BigDecimal remains) {
        Material material = getByCodeName(codeName);
        material.setRemains(remains);
        return update(material);
    }

    @Override
    public Material getByCodeName(String codeName) {
        return elements.values()
                .stream()
                .filter(material -> material.getCodeName().equals(codeName))
                .findAny().orElse(null);
    }
}
