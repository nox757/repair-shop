package ru.chibisov.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.chibisov.dao.UserDao;
import ru.chibisov.model.User;
import ru.chibisov.service.UserService;

public class UserServiceImpl implements UserService {

    private static final Logger log = LogManager.getLogger(UserServiceImpl.class.getName());

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        log.info("createService");
        this.userDao = userDao;
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public User addUser(User user) {
        return userDao.create(user);
    }

    @Override
    public User updateUser(User user) {
        return userDao.update(user);
    }
}
