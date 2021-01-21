package ru.chibisov.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import ru.chibisov.audit.annotation.Audit;
import ru.chibisov.audit.annotation.AuditCode;
import ru.chibisov.exception.BadUrlRequestException;
import ru.chibisov.resource.UserResource;
import ru.chibisov.resource.dto.UserDto;
import ru.chibisov.resource.dto.search.UserSearchDto;
import ru.chibisov.service.UserService;
import ru.chibisov.validator.UserDtoValidator;

import java.net.URI;

@RestController
public class UserController implements UserResource {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private UserService userService;
    private UserDtoValidator userDtoValidator;

    public UserController(UserService userService, UserDtoValidator userDtoValidator) {
        this.userService = userService;
        this.userDtoValidator = userDtoValidator;
    }

    @Override
    public UserDto getUserById(Long id) {
        log.debug("getUserById with {} - start", id);
        UserDto result = userService.getUserById(id);
        log.debug("getUserById end with result {}", result);
        return result;
    }

    @Override
    public Page<UserDto> getFilterUsers(UserSearchDto userSearchDto, Pageable pageable) {
        log.debug("getFilterUsers with {} - start", userSearchDto);
        Page<UserDto> result = userService.getUsers(userSearchDto, pageable);
        log.debug("getFilterUsers end with result {}", result.getContent());
        return result;
    }

    @Override
    @Audit(AuditCode.CREATE_USER)
    public ResponseEntity<UserDto> createUser(@Validated UserDto userDto, UriComponentsBuilder componentsBuilder) {
        log.debug("createUser with {} - start", userDto);
        UserDto result = userService.addUser(userDto);
        URI uri = componentsBuilder.path("/api/v1/users/" + result.getId()).buildAndExpand(result).toUri();
        ResponseEntity<UserDto> response = ResponseEntity.created(uri).body(result);
        log.debug("createUser end with result {}", result);
        return response;
    }

    @Override
    @Audit(AuditCode.UPDATE_USER)
    public UserDto updateUser(Long id, @Validated UserDto userDto) {
        log.debug("updateUser with {} - start", userDto);
        if (!userDto.getId().equals(id)) {
            throw new BadUrlRequestException("Path and body ID must be equal");
        }
        UserDto result = userService.updateUser(userDto.setId(id));
        log.debug("updateUser end with result {}", result);
        return result;
    }

    @Override
    @Audit(AuditCode.DELETE_USER)
    public void deleteUser(Long id) {
        log.debug("deleteUser with {} - start", id);
        userService.removeUserById(id);
        log.debug("deleteUser end with result {}", id);
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
