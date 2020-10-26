package ru.chibisov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chibisov.model.User;
import ru.chibisov.service.dto.UserDto;
import ru.chibisov.service.dto.mapper.UserMapper;
import ru.chibisov.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    private List<UserDto> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{id}")
    private UserDto getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    private UserDto createUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }

    @PutMapping(value = "/{id}")
    private UserDto updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @DeleteMapping
    private UserDto deleteUser(@RequestBody UserDto userDto) {
        return userService.deleteUser(userDto);
    }
}
