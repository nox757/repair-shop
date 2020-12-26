package ru.chibisov.resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;
import ru.chibisov.resource.dto.UserDto;
import ru.chibisov.resource.dto.search.UserSearchDto;

@RequestMapping("/api/v1/users")
public interface UserResource {

    @GetMapping(value = "{id}")
    UserDto getUserById(@PathVariable("id") Long id);

    @GetMapping
    Page<UserDto> getFilterUsers(@RequestBody UserSearchDto userSearchDto, Pageable pageable);

    @PostMapping
    ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto, UriComponentsBuilder componentsBuilder);

    @PutMapping(value = "{id}")
    UserDto updateUser(@PathVariable("id") Long id,
                       @RequestBody UserDto userDto);

    @DeleteMapping(value = "{id}")
    void deleteUser(@PathVariable("id") Long id);


}
