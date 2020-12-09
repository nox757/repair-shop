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
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.chibisov.dao.RequestDao;
import ru.chibisov.model.Request;
import ru.chibisov.model.Role;
import ru.chibisov.model.Status;
import ru.chibisov.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class RequestDaoImpl implements RequestDao {

    private static final Logger log = LoggerFactory.getLogger(RequestDaoImpl.class.getName());

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public RequestDaoImpl(JdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert, NamedParameterJdbcTemplate namedJdbcTemplate) {
        log.info("createRepository");
        this.jdbcTemplate = jdbcTemplate;
        this.namedJdbcTemplate = namedJdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
        this.simpleJdbcInsert.withTableName("request").usingGeneratedKeyColumns("request_id");
    }

    @Override
    public Request create(Request request) {
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("description", request.getDescription());
        parameters.put("status", request.getStatus().name());
        parameters.put("comment", request.getComment());
        parameters.put("repairer_id", request.getRepairer().getId());
        parameters.put("customer_id", request.getCustomer().getId());
        parameters.put("amount", request.getAmount());

        Number id = simpleJdbcInsert.executeAndReturnKey(parameters);
        request.setId(id.longValue());

        return request;
    }

    @Override
    public Request getById(Long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT request_id, description, status, comment, repairer_id, customer_id, amount, " +
                            " customer.name as customer_name, customer.email as customer_email, customer.phone as customer_phone, customer.role as customer_role, " +
                            " repairer.name as repairer_name, repairer.email as repairer_email, repairer.phone as repairer_phone, repairer.role as repairer_role " +
                            " FROM request req " +
                            " left join users customer on customer.user_id = req.customer_id" +
                            " left join users repairer on repairer.user_id = req.repairer_id" +
                            " WHERE request_id = ?",
                    new Object[]{id}, new RequestRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Request update(Request request) {
        final String sql = "update request set" +
                " description = :description ," +
                " status = :status ," +
                " comment = :comment ," +
                " repairer_id = :repairer.id ," +
                " customer_id = :customer.id ," +
                " amount = :amount " +
                " where request_id = :id";
        final BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(request);
        namedParameters.registerSqlType("status", Types.VARCHAR);
        if (namedJdbcTemplate.update(sql, namedParameters) == 0) {
            throw new RuntimeException("Some wrong update");
        }
        return getById(request.getId());
    }

    @Override
    public Request delete(Request request) {
        return deleteById(request.getId());
    }

    @Override
    public Request deleteById(Long id) {
        Request request = getById(id);
        if (request == null) {
            return null;
        }
        if (jdbcTemplate.update("DELETE FROM request WHERE request_id= ?", id) != 1) {
            throw new RuntimeException("Some wrong delete");
        }
        return request;
    }

    @Override
    public Collection<Request> getAll() {
        return jdbcTemplate.query("SELECT request_id, description, status, comment, repairer_id, customer_id, amount, " +
                " customer.name as customer_name, customer.email as customer_email, customer.phone as customer_phone, customer.role as customer_role, " +
                " repairer.name as repairer_name, repairer.email as repairer_email, repairer.phone as repairer_phone, repairer.role as repairer_role " +
                " FROM request req " +
                " left join users customer on customer.user_id = req.customer_id" +
                " left join users repairer on repairer.user_id = req.repairer_id", new RequestRowMapper());
    }

    @Override
    public Collection<Request> addAll(Collection<Request> requests) {
        final String sql = "INSERT INTO request(description, status, comment, repairer_id, customer_id, amount) " +
                " VALUES (:description, :status, :comment, :repairerId, :customerId, :amount)";
        final SqlParameterSource[] batch = createBatch(requests);
        int[] updatesCount = namedJdbcTemplate.batchUpdate(sql, batch);

        for (int i : updatesCount) {
            if (i != 1) {
                return null;
            }
        }

        //todo add uuid column generate before batch insert
        return requests;
    }

    private SqlParameterSource[] createBatch(Collection<?> candidates) {
        final SqlParameterSource[] batch = new SqlParameterSource[candidates.size()];
        int i = 0;
        for (Object candidate : candidates) {
            batch[i] = (candidate instanceof Map ? new MapSqlParameterSource((Map<String, ?>) candidate) :
                    new BeanPropertySqlParameterSource(candidate));
            if (candidate instanceof Request) {
                ((BeanPropertySqlParameterSource) batch[i]).registerSqlType("status", Types.VARCHAR);
            }

            i++;
        }
        return batch;
    }


    private final static class RequestRowMapper implements RowMapper<Request> {

        @Override
        public Request mapRow(ResultSet rs, int rowNum) throws SQLException {
            final Request request = new Request();
            request.setId(rs.getLong("request_id"));
            request.setDescription(rs.getString("description"));
            request.setStatus(Status.valueOf(rs.getString("status")));
            request.setComment(rs.getString("comment"));
            request.setAmount(rs.getBigDecimal("amount"));
            long customerId = rs.getLong("customer_id");
            if (customerId != 0) {
                final User customer = new User();
                customer.setId(customerId);
                customer.setEmail(rs.getString("customer_email"));
                customer.setName(rs.getString("customer_name"));
                customer.setPhone(rs.getString("customer_phone"));
                customer.setRole(Role.valueOf(rs.getString("customer_role")));
                request.setCustomer(customer);
            }
            long repairerId = rs.getLong("repairer_id");
            if (repairerId != 0) {
                final User repairer = new User();
                repairer.setId(repairerId);
                repairer.setEmail(rs.getString("repairer_email"));
                repairer.setName(rs.getString("repairer_name"));
                repairer.setPhone(rs.getString("repairer_phone"));
                repairer.setRole(Role.valueOf(rs.getString("repairer_role")));
                request.setRepairer(repairer);
            }
            return request;
        }
    }

}

