package ru.chibisov.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.chibisov.resource.dto.SupplierDto;
import ru.chibisov.exception.ObjectDtoValidationException;

@Component
public class SupplierDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return SupplierDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SupplierDto supplier = (SupplierDto) target;

        if (supplier.getOrgName() == null || supplier.getOrgName().isEmpty()) {
            errors.rejectValue("orgName", "orgName.empty", "Organization name must be fill");
        }

        if (supplier.getNameAgent() == null || supplier.getNameAgent().isEmpty()) {
            errors.rejectValue("agentName", "agentName.empty", "Agent name must be fill");
        }

        if (supplier.getPhoneAgent() == null || supplier.getPhoneAgent().isEmpty()) {
            errors.rejectValue("agentPhone", "agentPhone.empty", "Agent phone must be fill");
        }

        if (errors.hasErrors()) {
            throw new ObjectDtoValidationException(errors);
        }
    }
}