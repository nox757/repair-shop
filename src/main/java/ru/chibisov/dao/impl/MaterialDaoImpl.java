package ru.chibisov.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.chibisov.dao.MaterialDao;
import ru.chibisov.model.Material;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class MaterialDaoImpl implements MaterialDao {

    private static final Logger log = LoggerFactory.getLogger(MaterialDaoImpl.class.getName());

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public MaterialDaoImpl(JdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert, NamedParameterJdbcTemplate namedJdbcTemplate) {
        log.info("createRepository");
        this.jdbcTemplate = jdbcTemplate;
        this.namedJdbcTemplate = namedJdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
        this.simpleJdbcInsert.withTableName("material").usingGeneratedKeyColumns("material_id");
    }

    @Override
    public Material create(Material material) {
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("code_name", material.getCodeName());
        parameters.put("name", material.getName());
        parameters.put("price", material.getPrice());
        parameters.put("remains", material.getRemains());
        parameters.put("supplier_id", material.getSupplierId());

        Number id = simpleJdbcInsert.executeAndReturnKey(parameters);
        material.setId(id.longValue());

        return material;
    }

    @Override
    public Material getById(Long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM material WHERE material_id = ?", new Object[]{id}, new MaterialRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Material update(Material material) {
        final String sql = "update material set" +
                " code_name = :codeName ," +
                " name = :name ," +
                " price = :price ," +
                " remains = :remains ," +
                " supplier_id = :supplierId " +
                " where material_id = :id";
        final BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(material);
        if (namedJdbcTemplate.update(sql, namedParameters) == 0) {
            throw new RuntimeException("Some wrong update");
        }
        return material;
    }

    @Override
    public Material delete(Material material) {
        return deleteById(material.getId());
    }

    @Override
    public Material deleteById(Long id) {
        Material material = getById(id);
        if (material == null) {
            return null;
        }
        if (jdbcTemplate.update("DELETE FROM material WHERE material_id= ?", id) != 1) {
            throw new RuntimeException("Some wrong delete");
        }
        return material;
    }

    @Override
    public Collection<Material> getAll() {
        return jdbcTemplate.query("SELECT * FROM material", new MaterialRowMapper());
    }

    @Override
    public Material getByCodeName(String codeName) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM material WHERE code_name = ?", new Object[]{codeName}, new MaterialRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Collection<Material> addAll(Collection<Material> materials) {
        final String sql = "INSERT INTO material(code_name, name, price, remains, supplier_id) VALUES (:codeName, :name, :price, :remains, :supplierId)";
        final SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(materials);
        int[] updatesCount = namedJdbcTemplate.batchUpdate(sql, batch);

        for (int i : updatesCount) {
            if (i != 1) {
                throw new RuntimeException("Some wrong batch insert");
            }
        }

        //Необходимо, для простановки id в созданных записях
        final SqlParameterSource parameters = new MapSqlParameterSource("codeNames",
                materials.stream().map(Material::getCodeName).collect(Collectors.toList()));

        List<Material> materialsWithId = namedJdbcTemplate.query("SELECT * FROM material WHERE code_name IN (:codeNames)", parameters, new MaterialRowMapper());

        return materialsWithId;
    }

    @Override
    public List<Material> getBySupplierId(Long supplierId) {
        return jdbcTemplate.query("SELECT * FROM material where supplier_id = ?", new Object[]{supplierId}, new MaterialRowMapper());
    }

    private final static class MaterialRowMapper implements RowMapper<Material> {

        @Override
        public Material mapRow(ResultSet rs, int rowNum) throws SQLException {
            final Material material = new Material();
            material.setId(rs.getLong("material_id"));
            material.setCodeName(rs.getString("code_name"));
            material.setName(rs.getString("name"));
            material.setPrice(rs.getBigDecimal("price"));
            material.setRemains(rs.getBigDecimal("remains"));
            material.setSupplierId(rs.getLong("supplier_id"));
            return material;
        }
    }

}

