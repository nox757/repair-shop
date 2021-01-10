package ru.chibisov.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.chibisov.resource.dto.MaterialDto;
import ru.chibisov.exception.ObjectDtoValidationException;

@Component
public class MaterialDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return MaterialDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MaterialDto material = (MaterialDto) target;

        if (material.getCodeName() == null || material.getCodeName().isEmpty()) {
            errors.rejectValue("codeName", "codename.empty", "code name must be fill");
        }

        if (material.getName() == null || material.getName().isEmpty()) {
            errors.rejectValue("name", "name.empty", "name must be fill");
        }

        if (errors.hasErrors()) {
            throw new ObjectDtoValidationException(errors);
        }
    }
}