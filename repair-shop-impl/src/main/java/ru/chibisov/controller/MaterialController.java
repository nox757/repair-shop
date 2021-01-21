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
import ru.chibisov.resource.MaterialResource;
import ru.chibisov.resource.dto.MaterialDto;
import ru.chibisov.resource.dto.search.MaterialSearchDto;
import ru.chibisov.service.MaterialService;
import ru.chibisov.validator.MaterialDtoValidator;

import java.net.URI;

@RestController
public class MaterialController implements MaterialResource {

    private static final Logger log = LoggerFactory.getLogger(MaterialController.class);

    private MaterialService materialService;
    private MaterialDtoValidator materialDtoValidator;

    public MaterialController(MaterialService materialService, MaterialDtoValidator materialDtoValidator) {
        this.materialService = materialService;
        this.materialDtoValidator = materialDtoValidator;
    }

    @Override
    public MaterialDto getMaterialById(Long id) {
        log.debug("getMaterialById with {} - start", id);
        MaterialDto result = materialService.getMaterialById(id);
        log.debug("getMaterialById end with result {}", result);
        return result;
    }

    @Override
    public Page<MaterialDto> getFilterMaterials(MaterialSearchDto materialSearchDto, Pageable pageable) {
        log.debug("getFilerMaterials with {} - start", materialSearchDto);
        Page<MaterialDto> result = materialService.getMaterials(materialSearchDto, pageable);
        log.debug("getFilerMaterials end with result {}", result);
        return result;
    }

    @Override
    @Audit(value = AuditCode.CREATE_MATERIAL)
    public ResponseEntity<MaterialDto> createMaterial(@Validated MaterialDto materialDto, UriComponentsBuilder componentsBuilder) {
        log.debug("createMaterial with {} - start", materialDto);
        MaterialDto result = materialService.addMaterial(materialDto);
        URI uri = componentsBuilder.path("/api/v1/materials/" + result.getId()).buildAndExpand(result).toUri();
        ResponseEntity<MaterialDto> response = ResponseEntity.created(uri).body(result);
        log.debug("createMaterial end with result {}", result);
        return response;
    }

    @Override
    @Audit(AuditCode.UPDATE_MATERIAL)
    public MaterialDto updateMaterial(Long id, @Validated MaterialDto materialDto) {
        log.debug("updateMaterial with {} - start", materialDto);
        if (!materialDto.getId().equals(id)) {
            throw new BadUrlRequestException("Path and body ID must be equal");
        }
        MaterialDto result = materialService.updateMaterial(materialDto.setId(id));
        log.debug("updateMaterial end with result {}", result);
        return result;
    }

    @Override
    @Audit(AuditCode.DELETE_MATERIAL)
    public void deleteMaterial(Long id) {
        log.debug("deleteMaterial with {} - start", id);
        materialService.removeMaterialById(id);
        log.debug("deleteMaterial end with result {}", id);
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
