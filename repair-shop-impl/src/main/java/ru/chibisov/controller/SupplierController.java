package ru.chibisov.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import ru.chibisov.audit.annotation.Audit;
import ru.chibisov.audit.annotation.AuditCode;
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

    private static final Logger log = LoggerFactory.getLogger(SupplierController.class);

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
        log.debug("getSupplierById with {} - start", id);
        SupplierDto result = supplierService.getSupplierById(id);
        log.debug("getSupplierById end with result {}", result);
        return result;
    }

    @Override
    public Page<SupplierDto> getFilterSupplier(SupplierSearchDto supplierSearchDto, Pageable pageable) {
        log.debug("getFilterSupplier with {} - start", supplierSearchDto);
        Page<SupplierDto> result = supplierService.getSuppliers(supplierSearchDto, pageable);
        log.debug("getFilterSupplier end with result {}", result.getContent());
        return result;
    }

    @Override
    @Audit(AuditCode.CREATE_SUPPLIER)
    public ResponseEntity<SupplierDto> createSupplier(@Validated SupplierDto supplierDto, UriComponentsBuilder componentsBuilder) {
        log.debug("createSupplier with {} - start", supplierDto);
        SupplierDto result = supplierService.addSupplier(supplierDto);
        URI uri = componentsBuilder.path("/api/v1/suppliers/" + result.getId()).buildAndExpand(result).toUri();
        ResponseEntity<SupplierDto> response = ResponseEntity.created(uri).body(result);
        log.debug("createSupplier end with result {}", result);
        return response;
    }

    @Override
    @Audit(AuditCode.UPDATE_SUPPLIER)
    public SupplierDto updateSupplier(Long id, @Validated SupplierDto supplierDto) {
        log.debug("updateSupplier with {} - start", id, supplierDto);
        if (!supplierDto.getId().equals(id)) {
            throw new BadUrlRequestException("Path and body ID must be equal");
        }
        SupplierDto result = supplierService.updateSupplier(supplierDto.setId(id));
        log.debug("updateSupplier end with result {}", result);
        return result;
    }

    @Override
    @Audit(AuditCode.DELETE_SUPPLIER)
    public void deleteSupplier(Long id) {
        log.debug("deleteSupplier with {} - start", id);
        supplierService.removeSupplierById(id);
        log.debug("deleteSupplier end with result {}", id);
    }

    @Override
    public List<MaterialDto> getMaterialsBySupplierId(Long id) {
        log.debug("getMaterialsBySupplierId with {} - start", id);
        List<MaterialDto> result = materialService.getAllMaterialsBySupplierId(id);
        log.debug("getMaterialsBySupplierId end with result {}", result);
        return result;
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
