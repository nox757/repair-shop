package ru.chibisov.controller.dto.mapper;

import org.mapstruct.Mapper;
import ru.chibisov.resource.dto.UserDto;
import ru.chibisov.model.User;

import java.util.List;

@Mapper
public interface UserMapper {

    UserDto map(User user);

    User map(UserDto userDto);

    List<UserDto> map(List<User> users);

}
