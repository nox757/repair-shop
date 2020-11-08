package ru.chibisov.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.chibisov.controller.dto.RequestDto;
import ru.chibisov.exception.ObjectDtoValidationException;

@Component
public class RequestDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return RequestDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RequestDto request = (RequestDto) target;

        if (request.getNameCustomer() == null || request.getNameCustomer().isEmpty()) {
            errors.rejectValue("nameCustomer", "nameCustomer.empty", "customer name must be fill");
        }

        if (request.getCustomerId() == null) {
            errors.rejectValue("customerId", "customerId.empty", "customer id must be fill");
        }

        if (request.getDescription() == null || request.getDescription().isEmpty()) {
            errors.rejectValue("description", "description.empty", "description must be fill");
        }

        if (errors.hasErrors()) {
            throw new ObjectDtoValidationException(errors);
        }
    }
}
