package ru.chibisov.controller;

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
import ru.chibisov.controller.dto.SupplierDto;
import ru.chibisov.service.SupplierService;
import ru.chibisov.validator.SupplierDtoValidator;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    private SupplierService supplierService;
    private SupplierDtoValidator supplierDtoValidator;

    public SupplierController(SupplierService supplierService, SupplierDtoValidator supplierDtoValidator) {
        this.supplierService = supplierService;
        this.supplierDtoValidator = supplierDtoValidator;
    }

    @GetMapping
    private List<SupplierDto> getSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping(value = "/{id}")
    private SupplierDto getSupplierById(@PathVariable("id") Long id) {
        return supplierService.getSupplierById(id);
    }

    @PostMapping
    private SupplierDto createSupplier(@Validated @RequestBody SupplierDto supplierDto) {
        return supplierService.addSupplier(supplierDto);
    }

    @PutMapping(value = "/{id}")
    private SupplierDto updateSupplier(@PathVariable("id") Long id,
                                       @Validated @RequestBody SupplierDto supplierDto) {
        return supplierService.updateSupplier(supplierDto.setId(id));
    }

    @DeleteMapping(value = "/{id}")
    private void deleteSupplier(@PathVariable("id") Long id) {
        supplierService.removeSupplierById(id);
    }

    @ModelAttribute
    public SupplierDto supplierDto() {
        return new SupplierDto();
    }

    @InitBinder(value = "supplierDto")
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(supplierDtoValidator);
    }
}
