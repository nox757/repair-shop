package ru.chibisov.controller;

import org.springframework.http.ResponseEntity;
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
import org.springframework.web.util.UriComponentsBuilder;
import ru.chibisov.controller.dto.SupplierDto;
import ru.chibisov.exception.BadUrlRequestException;
import ru.chibisov.service.SupplierService;
import ru.chibisov.validator.SupplierDtoValidator;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/suppliers")
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
    private ResponseEntity<SupplierDto> createSupplier(@Validated @RequestBody SupplierDto supplierDto, UriComponentsBuilder componentsBuilder) {
        SupplierDto result = supplierService.addSupplier(supplierDto);
        URI uri = componentsBuilder.path("/api/v1/suppliers/" + result.getId()).buildAndExpand(result).toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @PutMapping(value = "/{id}")
    private SupplierDto updateSupplier(@PathVariable("id") Long id,
                                       @Validated @RequestBody SupplierDto supplierDto) {
        if (!supplierDto.getId().equals(id)) {
            throw new BadUrlRequestException("Path and body ID must be equal");
        }
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
