package ru.chibisov.dao.impl;

import org.springframework.stereotype.Repository;
import ru.chibisov.dao.UserDao;
import ru.chibisov.model.Role;
import ru.chibisov.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserDaoImpl extends AbstractDao<User, Long> implements UserDao {

    public UserDaoImpl() {
        super(User.class, new HashMap<>());
    }

    @Override
    public List<User> getAllCustomer() {
        return elements.values()
                .stream()
                .filter(user -> user.getRole().equals(Role.CUSTOMER))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getAllRepairer() {
        return elements.values()
                .stream()
                .filter(user -> user.getRole().equals(Role.REPAIRER))
                .collect(Collectors.toList());
    }
}
