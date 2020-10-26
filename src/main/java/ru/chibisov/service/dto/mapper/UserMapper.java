package ru.chibisov.service.dto.mapper;

import org.mapstruct.Mapper;
import ru.chibisov.model.User;
import ru.chibisov.service.dto.UserDto;

import java.util.List;

@Mapper
public interface UserMapper {

    UserDto map(User user);

    User map(UserDto userDto);

    List<UserDto> map(List<User> users);

}
