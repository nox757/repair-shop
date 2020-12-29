package ru.chibisov.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import ru.chibisov.controller.dto.UserDto;
import ru.chibisov.controller.dto.search.UserSearchDto;
import ru.chibisov.exception.BadUrlRequestException;
import ru.chibisov.service.UserService;
import ru.chibisov.validator.UserDtoValidator;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;
    private UserDtoValidator userDtoValidator;

    public UserController(UserService userService, UserDtoValidator userDtoValidator) {
        this.userService = userService;
        this.userDtoValidator = userDtoValidator;
    }

    @GetMapping(value = "{id}")
    private UserDto getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public Page<UserDto> getFilterUsers(@RequestBody UserSearchDto userSearchDto, Pageable pageable) {
        return userService.getUsers(userSearchDto, pageable);
    }

    @PostMapping
    private ResponseEntity<UserDto> createUser(@Validated @RequestBody UserDto userDto, UriComponentsBuilder componentsBuilder) {
        UserDto result = userService.addUser(userDto);
        URI uri = componentsBuilder.path("/api/v1/users/" + result.getId()).buildAndExpand(result).toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @PutMapping(value = "{id}")
    private UserDto updateUser(@PathVariable("id") Long id,
                               @Validated @RequestBody UserDto userDto) {
        if (!userDto.getId().equals(id)) {
            throw new BadUrlRequestException("Path and body ID must be equal");
        }
        return userService.updateUser(userDto.setId(id));
    }

    @DeleteMapping(value = "{id}")
    private void deleteUser(@PathVariable("id") Long id) {
        userService.removeUserById(id);
    }

    @ModelAttribute
    public UserDto userDto() {
        return new UserDto();
    }

    @InitBinder(value = "userDto")
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(userDtoValidator);
    }

}
