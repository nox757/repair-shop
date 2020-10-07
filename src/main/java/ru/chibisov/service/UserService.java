package ru.chibisov.service;

import ru.chibisov.model.User;

public interface UserService {

    User getUserById(Long id);

    User addUser(User user);

    User updateUser(User user);
}
