package ru.chibisov.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Primary;
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
import ru.chibisov.dao.SupplierDao;
import ru.chibisov.model.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class SupplierDaoImpl implements SupplierDao {

    private static final Logger log = LogManager.getLogger(SupplierDaoImpl.class.getName());

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public SupplierDaoImpl(JdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert, NamedParameterJdbcTemplate namedJdbcTemplate) {
        log.info("createRepository");
        this.jdbcTemplate = jdbcTemplate;
        this.namedJdbcTemplate = namedJdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
        this.simpleJdbcInsert.withTableName("supplier").usingGeneratedKeyColumns("supplier_id");
    }

    @Override
    public Supplier create(Supplier supplier) {
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("org_name", supplier.getOrgName());
        parameters.put("name_agent", supplier.getNameAgent());
        parameters.put("phone_agent", supplier.getPhoneAgent());

        Number id = simpleJdbcInsert.executeAndReturnKey(parameters);
        supplier.setId(id.longValue());

        return supplier;
    }

    @Override
    public Supplier getById(Long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM supplier WHERE supplier_id = ?", new Object[]{id}, new SupplierRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Supplier update(Supplier supplier) {
        final String sql = "update supplier set" +
                " org_name = :orgName ," +
                " name_agent = :nameAgent ," +
                " phone_agent = :phoneAgent " +
                " where supplier_id = :id";
        final BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(supplier);
        if (namedJdbcTemplate.update(sql, namedParameters) == 0) {
            throw new RuntimeException("Some wrong update");
        }
        return supplier;
    }

    @Override
    public Supplier delete(Supplier supplier) {
        return deleteById(supplier.getId());
    }

    @Override
    public Supplier deleteById(Long id) {
        Supplier supplier = getById(id);
        if (supplier == null) {
            return null;
        }
        if (jdbcTemplate.update("DELETE FROM supplier WHERE supplier_id= ?", id) != 1) {
            throw new RuntimeException("Some wrong delete");
        }
        return supplier;
    }

    @Override
    public Collection<Supplier> getAll() {
        return jdbcTemplate.query("SELECT * FROM supplier", new SupplierRowMapper());
    }

    @Override
    public Collection<Supplier> addAll(Collection<Supplier> suppliers) {
        final String sql = "INSERT INTO supplier(org_name, name_agent, phone_agent) VALUES (:orgName, :nameAgent, :phoneAgent)";
        final SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(suppliers);
        int[] updatesCount = namedJdbcTemplate.batchUpdate(sql, batch);

        for (int i : updatesCount) {
            if (i != 1) {
                throw new RuntimeException("Some wrong batch insert");
            }
        }

        //todo add uniqieue column
//        final SqlParameterSource parameters = new MapSqlParameterSource("orgNames",
//                suppliers.stream().map(Supplier::getOrgName).collect(Collectors.toList())
//        );
//        List<Supplier> suppliersWithId = namedJdbcTemplate.query("SELECT * FROM supplier WHERE org_name IN (:orgNames)", parameters, new SupplierRowMapper());
//        return suppliersWithId;
        return suppliers;
    }

    private final static class SupplierRowMapper implements RowMapper<Supplier> {

        @Override
        public Supplier mapRow(ResultSet rs, int rowNum) throws SQLException {
            final Supplier supplier = new Supplier();
            supplier.setId(rs.getLong("supplier_id"));
            supplier.setOrgName(rs.getString("org_name"));
            supplier.setNameAgent(rs.getString("name_agent"));
            supplier.setPhoneAgent(rs.getString("phone_agent"));
            return supplier;
        }
    }

}

