package ru.chibisov.resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;
import ru.chibisov.resource.dto.MaterialDto;
import ru.chibisov.resource.dto.SupplierDto;
import ru.chibisov.resource.dto.search.SupplierSearchDto;

import java.util.List;

@RequestMapping("/api/v1/suppliers")
public interface SupplierResource {

    @GetMapping(value = "{id}")
    SupplierDto getSupplierById(@PathVariable("id") Long id);

    @GetMapping
    Page<SupplierDto> getFilterSupplier(@RequestBody SupplierSearchDto supplierSearchDto, Pageable pageable);

    @PostMapping
    ResponseEntity<SupplierDto> createSupplier(@RequestBody SupplierDto supplierDto, UriComponentsBuilder componentsBuilder);

    @PutMapping(value = "{id}")
    SupplierDto updateSupplier(@PathVariable("id") Long id,
                               @RequestBody SupplierDto supplierDto);

    @DeleteMapping(value = "{id}")
    void deleteSupplier(@PathVariable("id") Long id);

    @GetMapping(value = "{id}/materials")
    List<MaterialDto> getMaterialsBySupplierId(@PathVariable("id") Long id);


}
