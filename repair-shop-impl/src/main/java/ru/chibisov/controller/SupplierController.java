package ru.chibisov.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import ru.chibisov.exception.BadUrlRequestException;
import ru.chibisov.resource.SupplierResource;
import ru.chibisov.resource.dto.MaterialDto;
import ru.chibisov.resource.dto.SupplierDto;
import ru.chibisov.resource.dto.search.SupplierSearchDto;
import ru.chibisov.service.MaterialService;
import ru.chibisov.service.SupplierService;
import ru.chibisov.validator.SupplierDtoValidator;

import java.net.URI;
import java.util.List;

@RestController
public class SupplierController implements SupplierResource {

    private SupplierService supplierService;
    private SupplierDtoValidator supplierDtoValidator;
    private MaterialService materialService;

    public SupplierController(SupplierService supplierService, SupplierDtoValidator supplierDtoValidator, MaterialService materialService) {
        this.supplierService = supplierService;
        this.supplierDtoValidator = supplierDtoValidator;
        this.materialService = materialService;
    }

    @Override
    public SupplierDto getSupplierById(Long id) {
        return supplierService.getSupplierById(id);
    }

    @Override
    public Page<SupplierDto> getFilterSupplier(SupplierSearchDto supplierSearchDto, Pageable pageable) {
        return supplierService.getSuppliers(supplierSearchDto, pageable);
    }

    @Override
    public ResponseEntity<SupplierDto> createSupplier(@Validated SupplierDto supplierDto, UriComponentsBuilder componentsBuilder) {
        SupplierDto result = supplierService.addSupplier(supplierDto);
        URI uri = componentsBuilder.path("/api/v1/suppliers/" + result.getId()).buildAndExpand(result).toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @Override
    public SupplierDto updateSupplier(Long id, @Validated SupplierDto supplierDto) {
        if (!supplierDto.getId().equals(id)) {
            throw new BadUrlRequestException("Path and body ID must be equal");
        }
        return supplierService.updateSupplier(supplierDto.setId(id));
    }

    @Override
    public void deleteSupplier(Long id) {
        supplierService.removeSupplierById(id);
    }

    @Override
    public List<MaterialDto> getMaterialsBySupplierId(Long id) {
        return materialService.getAllMaterialsBySupplierId(id);
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
