package ru.chibisov.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chibisov.controller.dto.SupplierDto;
import ru.chibisov.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    private SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
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
    private SupplierDto createSupplier(@RequestBody SupplierDto supplierDto) {
        return supplierService.addSupplier(supplierDto);
    }

    @PutMapping(value = "/{id}")
    private SupplierDto updateSupplier(@PathVariable("id") Long id,
                                       @RequestBody SupplierDto supplierDto) {
        return supplierService.updateSupplier(supplierDto.setId(id));
    }

    @DeleteMapping(value = "/{id}")
    private void deleteSupplier(@PathVariable("id") Long id) {
        supplierService.removeSupplierById(id);
    }
}
