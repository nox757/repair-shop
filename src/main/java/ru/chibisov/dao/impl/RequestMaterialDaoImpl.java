package ru.chibisov.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.chibisov.dao.RequestMaterialDao;
import ru.chibisov.model.RequestMaterial;
import ru.chibisov.model.RequestMaterialPk;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Repository
public class RequestMaterialDaoImpl implements RequestMaterialDao {

    private static final Logger log = LoggerFactory.getLogger(RequestMaterialDaoImpl.class.getName());

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public RequestMaterialDaoImpl(JdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert, NamedParameterJdbcTemplate namedJdbcTemplate) {
        log.info("createRepository");
        this.jdbcTemplate = jdbcTemplate;
        this.namedJdbcTemplate = namedJdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
        this.simpleJdbcInsert.withTableName("request_requestMaterial");
    }

    @Override
    public RequestMaterial create(RequestMaterial requestMaterial) {
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("quantity", requestMaterial.getQuantity());
        parameters.put("material_id", requestMaterial.getId().getMaterialId());
        parameters.put("request_id", requestMaterial.getId().getRequestId());

        if (simpleJdbcInsert.execute(parameters) != 1) {
            throw new RuntimeException("Some wrong create");
        }

        return requestMaterial;
    }

    @Override
    public RequestMaterial getById(RequestMaterialPk id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM request_material WHERE request_id = ? and material_id = ?", new Object[]{id.getRequestId(), id.getMaterialId()}, new RequestMaterialRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public RequestMaterial update(RequestMaterial requestMaterial) {
        final String sql = "update request_material set" +
                " quantity = :quantity" +
                " where request_id = :id.requestId and " +
                " material_id = :id.materialId ";
        final BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(requestMaterial);
        if (namedJdbcTemplate.update(sql, namedParameters) == 0) {
            throw new RuntimeException("Some wrong update");
        }
        return requestMaterial;
    }

    @Override
    public RequestMaterial delete(RequestMaterial requestMaterial) {
        return deleteById(requestMaterial.getId());
    }

    @Override
    public RequestMaterial deleteById(RequestMaterialPk id) {
        RequestMaterial requestMaterial = getById(id);
        if (requestMaterial == null) {
            return null;
        }
        if (jdbcTemplate.update("DELETE FROM request_material WHERE request_id= ? and material_id = ?", id.getRequestId(), id.getMaterialId()) != 1) {
            throw new RuntimeException("Some wrong delete");
        }
        return requestMaterial;
    }

    @Override
    public Collection<RequestMaterial> getAll() {
        return jdbcTemplate.query("SELECT * FROM request_material", new RequestMaterialRowMapper());
    }

    @Override
    public Collection<RequestMaterial> addAll(Collection<RequestMaterial> requestMaterials) {
        final String sql = "INSERT INTO request_material(quantity, material_id, request_id) " +
                " VALUES (:quantity, :id.materialId, :id.requestId)";
        final SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(requestMaterials);
        int[] updatesCount = namedJdbcTemplate.batchUpdate(sql, batch);

        for (int i : updatesCount) {
            if (i != 1) {
                throw new RuntimeException("Some wrong batch insert");
            }
        }

        return requestMaterials;
    }

    @Override
    public Set<RequestMaterial> addOrUpdateAll(Set<RequestMaterial> requestMaterials) {
        final String sql = "INSERT INTO request_material(quantity, material_id, request_id) " +
                " VALUES (:quantity, :id.materialId, :id.requestId) " +
                " ON CONFLICT ON CONSTRAINT request_material_pk DO UPDATE SET quantity = :quantity";
        final SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(requestMaterials);
        int[] updatesCount = namedJdbcTemplate.batchUpdate(sql, batch);

        for (int i : updatesCount) {
            if (i != 1) {
                throw new RuntimeException("Some wrong batch insert or update");
            }
        }
        return requestMaterials;
    }

    @Override
    public Set<RequestMaterial> getByRequestId(Long requestId) {
        return new HashSet<>(jdbcTemplate.query("SELECT * FROM request_material where request_id = ?", new Object[]{requestId}, new RequestMaterialRowMapper()));
    }

    @Override
    public void deleteByRequestId(Long requestId) {
        jdbcTemplate.update("DELETE FROM request_material WHERE request_id= ?", requestId);
    }

    private final static class RequestMaterialRowMapper implements RowMapper<RequestMaterial> {

        @Override
        public RequestMaterial mapRow(ResultSet rs, int rowNum) throws SQLException {
            final RequestMaterial requestMaterial = new RequestMaterial();
            requestMaterial.setId(new RequestMaterialPk(rs.getLong("material_id"), rs.getLong("request_id")));
            requestMaterial.setQuantity(rs.getBigDecimal("quantity"));
            return requestMaterial;
        }
    }

}


