package ru.chibisov.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.chibisov.controller.dto.UserDto;
import ru.chibisov.exception.ObjectDtoValidationException;

@Component
public class UserDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto user = (UserDto) target;

        if (user.getName() == null || user.getName().isEmpty()) {
            errors.rejectValue("name", "name.empty", "name must be fill");
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            errors.rejectValue("email", "email.empty", "email must be fill");
        }

        if (errors.hasErrors()) {
            throw new ObjectDtoValidationException(errors);
        }
    }
}
