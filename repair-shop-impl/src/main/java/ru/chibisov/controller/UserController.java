package ru.chibisov.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import ru.chibisov.exception.BadUrlRequestException;
import ru.chibisov.resource.UserResource;
import ru.chibisov.resource.dto.UserDto;
import ru.chibisov.resource.dto.search.UserSearchDto;
import ru.chibisov.service.UserService;
import ru.chibisov.validator.UserDtoValidator;

import java.net.URI;

@RestController
public class UserController implements UserResource {

    private UserService userService;
    private UserDtoValidator userDtoValidator;

    public UserController(UserService userService, UserDtoValidator userDtoValidator) {
        this.userService = userService;
        this.userDtoValidator = userDtoValidator;
    }

    @Override
    public UserDto getUserById(Long id) {
        return userService.getUserById(id);
    }

    @Override
    public Page<UserDto> getFilterUsers(UserSearchDto userSearchDto, Pageable pageable) {
        return userService.getUsers(userSearchDto, pageable);
    }

    @Override
    public ResponseEntity<UserDto> createUser(@Validated UserDto userDto, UriComponentsBuilder componentsBuilder) {
        UserDto result = userService.addUser(userDto);
        URI uri = componentsBuilder.path("/api/v1/users/" + result.getId()).buildAndExpand(result).toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @Override
    public UserDto updateUser(Long id, @Validated UserDto userDto) {
        if (!userDto.getId().equals(id)) {
            throw new BadUrlRequestException("Path and body ID must be equal");
        }
        return userService.updateUser(userDto.setId(id));
    }

    @Override
    public void deleteUser(Long id) {
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
