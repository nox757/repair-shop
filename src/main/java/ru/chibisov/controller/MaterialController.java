package ru.chibisov.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import ru.chibisov.controller.dto.MaterialDto;
import ru.chibisov.controller.dto.UserDto;
import ru.chibisov.controller.dto.search.MaterialSearchDto;
import ru.chibisov.controller.dto.search.UserSearchDto;
import ru.chibisov.exception.BadUrlRequestException;
import ru.chibisov.service.MaterialService;
import ru.chibisov.validator.MaterialDtoValidator;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/materials")
public class MaterialController {

    private MaterialService materialService;
    private MaterialDtoValidator materialDtoValidator;

    public MaterialController(MaterialService materialService, MaterialDtoValidator materialDtoValidator) {
        this.materialService = materialService;
        this.materialDtoValidator = materialDtoValidator;
    }

    @GetMapping(value = "{id}")
    private MaterialDto getMaterialById(@PathVariable("id") Long id) {
        return materialService.getMaterialById(id);
    }

    @GetMapping
    public Page<MaterialDto> getFilterMaterials(@RequestBody MaterialSearchDto materialSearchDto, Pageable pageable) {
        return materialService.getMaterials(materialSearchDto, pageable);
    }

    @PostMapping
    private ResponseEntity<MaterialDto> createMaterial(@Validated @RequestBody MaterialDto materialDto, UriComponentsBuilder componentsBuilder) {
        MaterialDto result = materialService.addMaterial(materialDto);
        URI uri = componentsBuilder.path("/api/v1/materials/" + result.getId()).buildAndExpand(result).toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @PutMapping(value = "{id}")
    private MaterialDto updateMaterial(@PathVariable("id") Long id,
                                       @Validated @RequestBody MaterialDto materialDto) {
        if (!materialDto.getId().equals(id)) {
            throw new BadUrlRequestException("Path and body ID must be equal");
        }
        return materialService.updateMaterial(materialDto.setId(id));
    }

    @DeleteMapping(value = "{id}")
    private void deleteMaterial(@PathVariable("id") Long id) {
        materialService.removeMaterialById(id);
    }

    @ModelAttribute
    public MaterialDto materialDto() {
        return new MaterialDto();
    }

    @InitBinder(value = "materialDto")
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(materialDtoValidator);
    }
}
