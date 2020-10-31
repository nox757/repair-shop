package ru.chibisov.service;

import ru.chibisov.controller.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto getUserById(Long id);

    UserDto addUser(UserDto user);

    UserDto updateUser(UserDto user);

    UserDto removeUser(UserDto user);

    List<UserDto> getAllUsers();
}
