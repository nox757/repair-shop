package ru.chibisov.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chibisov.controller.dto.UserDto;
import ru.chibisov.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

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
    private UserDto updateUser(@PathVariable("id") Long id,
                               @RequestBody UserDto userDto) {
        return userService.updateUser(userDto.setId(id));
    }

    @DeleteMapping(value = "/{id}")
    private void deleteUser(@PathVariable("id") Long id) {
        userService.removeUserById(id);
    }
}
