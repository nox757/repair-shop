package ru.chibisov.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.chibisov.dao.UserDao;
import ru.chibisov.model.User;
import ru.chibisov.service.UserService;
import ru.chibisov.service.dto.UserDto;
import ru.chibisov.service.dto.mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LogManager.getLogger(UserServiceImpl.class.getName());

    private UserDao userDao;
    private UserMapper mapper;

    @Autowired
    public UserServiceImpl(UserDao userDao, UserMapper mapper) {
        log.info("createService");
        this.userDao = userDao;
        this.mapper = mapper;
    }

    @Override
    public UserDto getUserById(Long id) {
        return mapper.map(userDao.getById(id));
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = mapper.map(userDto);
        return mapper.map(userDao.create(user));
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = mapper.map(userDto);
        return mapper.map(userDao.update(user));
    }

    @Override
    public UserDto deleteUser(UserDto userDto) {
        User user = mapper.map(userDto);
        return mapper.map(userDao.delete(user));
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = new ArrayList<>(userDao.getAll());
        return mapper.map(users);
    }
}
