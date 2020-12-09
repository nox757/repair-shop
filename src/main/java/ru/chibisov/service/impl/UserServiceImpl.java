package ru.chibisov.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chibisov.controller.dto.UserDto;
import ru.chibisov.controller.dto.mapper.UserMapper;
import ru.chibisov.dao.UserDao;
import ru.chibisov.exception.ObjectNotFoundException;
import ru.chibisov.model.User;
import ru.chibisov.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class.getName());

    private UserDao userDao;
    private UserMapper mapper;

    public UserServiceImpl(UserDao userDao, UserMapper mapper) {
        log.info("createService");
        this.userDao = userDao;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUserById(Long id) {
        User user = userDao.getById(id);
        if (user == null) {
            throw new ObjectNotFoundException(String.valueOf(id));
        }
        return mapper.map(user);
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
    public void removeUserById(Long id) {
        if (userDao.deleteById(id) == null) {
            throw new ObjectNotFoundException(String.valueOf(id));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        List<User> users = new ArrayList<>(userDao.getAll());
        return mapper.map(users);
    }
}
