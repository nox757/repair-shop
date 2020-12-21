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
import ru.chibisov.dao.UserDao;
import ru.chibisov.model.Role;
import ru.chibisov.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger log = LoggerFactory.getLogger(UserDaoImpl.class.getName());

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public UserDaoImpl(JdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert, NamedParameterJdbcTemplate namedJdbcTemplate) {
        log.info("createRepository");
        this.jdbcTemplate = jdbcTemplate;
        this.namedJdbcTemplate = namedJdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
        this.simpleJdbcInsert.withTableName("users").usingGeneratedKeyColumns("user_id");
    }

    @Override
    public User create(User user) {
        log.debug("Created " + user.toString());
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("role", user.getRole().name());
        parameters.put("name", user.getName());
        parameters.put("password", user.getPassword());
        parameters.put("phone", user.getPhone());
        parameters.put("email", user.getEmail());

        Number id = simpleJdbcInsert.executeAndReturnKey(parameters);
        user.setId(id.longValue());

        return user;
    }

    @Override
    public User getById(Long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM users WHERE user_id = ?", new Object[]{id}, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public User update(User user) {
        final String sql = "update users set" +
                " role = :role ," +
                " name = :name ," +
                " password = :password ," +
                " phone = :phone ," +
                " email = :email" +
                " where user_id = :id";
        final BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);
        namedParameters.registerSqlType("role", Types.VARCHAR);
        if (namedJdbcTemplate.update(sql, namedParameters) == 0) {
            throw new RuntimeException("Some wrong update");
        }
        return user;
    }

    @Override
    public User delete(User user) {
        return deleteById(user.getId());
    }

    @Override
    public User deleteById(Long id) {
        User user = getById(id);
        if (user == null) {
            return null;
        }
        if (jdbcTemplate.update("DELETE FROM users WHERE user_id= ?", id) != 1) {
            throw new RuntimeException("Some wrong delete");
        }
        return user;
    }

    @Override
    public Collection<User> getAll() {
        return jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
    }

    @Override
    public List<User> getAllCustomer() {
        return jdbcTemplate.query("SELECT * FROM users where role = ?", new Object[]{Role.CUSTOMER.name()}, new UserRowMapper());
    }

    @Override
    public List<User> getAllRepairer() {
        return jdbcTemplate.query("SELECT * FROM users where role = ?", new Object[]{Role.REPAIRER.name()}, new UserRowMapper());
    }

    @Override
    public Collection<User> addAll(Collection<User> users) {
        final String sql = "INSERT INTO users(name, role, password, email, phone) VALUES (:name, :role, :password, :email, :phone)";
        final SqlParameterSource[] batch = createBatch(users);
        int[] updatesCount = namedJdbcTemplate.batchUpdate(sql, batch);

        for (int i : updatesCount) {
            if (i != 1) {
                throw new RuntimeException("Some wrong update");
            }
        }

        //todo add unique column or constraint emails
//        final SqlParameterSource parameters = new MapSqlParameterSource("emails",
//                users.stream().map(User::getEmail).collect(Collectors.toList())
//        );
//
//        List<User> usersWithId = namedJdbcTemplate.query("SELECT * FROM users WHERE email IN (:emails)", parameters, new UserRowMapper());

        return users;
    }

    private SqlParameterSource[] createBatch(Collection<?> candidates) {
        final SqlParameterSource[] batch = new SqlParameterSource[candidates.size()];
        int i = 0;
        for (Object candidate : candidates) {
            batch[i] = (candidate instanceof Map ? new MapSqlParameterSource((Map<String, ?>) candidate) :
                    new BeanPropertySqlParameterSource(candidate));
            if (candidate instanceof User) {
                ((BeanPropertySqlParameterSource) batch[i]).registerSqlType("role", Types.VARCHAR);
            }

            i++;
        }
        return batch;
    }

    private final static class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            final User user = new User();
            user.setId(rs.getLong("user_id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setRole(Role.valueOf(rs.getString("role")));
            user.setEmail(rs.getString("email"));
            user.setPhone(rs.getString("phone"));
            return user;
        }
    }

}

