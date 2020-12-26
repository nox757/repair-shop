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
import ru.chibisov.resource.dto.search.MaterialSearchDto;

@RequestMapping("/api/v1/materials")
public interface MaterialResource {

    @GetMapping(value = "{id}")
    MaterialDto getMaterialById(@PathVariable("id") Long id);

    @GetMapping
    Page<MaterialDto> getFilterUsers(@RequestBody MaterialSearchDto materialSearchDto, Pageable pageable);

    @PostMapping
    ResponseEntity<MaterialDto> createMaterial(@RequestBody MaterialDto materialDto, UriComponentsBuilder componentsBuilder);

    @PutMapping(value = "{id}")
    MaterialDto updateMaterial(@PathVariable("id") Long id, @RequestBody MaterialDto materialDto);

    @DeleteMapping(value = "{id}")
    void deleteMaterial(@PathVariable("id") Long id);

}
