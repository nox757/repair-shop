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
import ru.chibisov.resource.MaterialResource;
import ru.chibisov.resource.dto.MaterialDto;
import ru.chibisov.resource.dto.search.MaterialSearchDto;
import ru.chibisov.service.MaterialService;
import ru.chibisov.validator.MaterialDtoValidator;

import java.net.URI;

@RestController
public class MaterialController implements MaterialResource {

    private MaterialService materialService;
    private MaterialDtoValidator materialDtoValidator;

    public MaterialController(MaterialService materialService, MaterialDtoValidator materialDtoValidator) {
        this.materialService = materialService;
        this.materialDtoValidator = materialDtoValidator;
    }

    @Override
    public MaterialDto getMaterialById(Long id) {
        return materialService.getMaterialById(id);
    }

    @Override
    public Page<MaterialDto> getFilterMaterials(MaterialSearchDto materialSearchDto, Pageable pageable) {
        return materialService.getMaterials(materialSearchDto, pageable);
    }

    @Override
    public ResponseEntity<MaterialDto> createMaterial(@Validated MaterialDto materialDto, UriComponentsBuilder componentsBuilder) {
        MaterialDto result = materialService.addMaterial(materialDto);
        URI uri = componentsBuilder.path("/api/v1/materials/" + result.getId()).buildAndExpand(result).toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @Override
    public MaterialDto updateMaterial(Long id, @Validated MaterialDto materialDto) {
        if (!materialDto.getId().equals(id)) {
            throw new BadUrlRequestException("Path and body ID must be equal");
        }
        return materialService.updateMaterial(materialDto.setId(id));
    }

    @Override
    public void deleteMaterial(Long id) {
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
