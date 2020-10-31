package ru.chibisov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chibisov.service.MaterialService;
import ru.chibisov.controller.dto.MaterialDto;

import java.util.List;

@RestController
@RequestMapping("/material")
public class MaterialController {

    private MaterialService materialService;

    @Autowired
    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping
    private List<MaterialDto> getMaterials() {
        return materialService.getAllMaterials();
    }

    @GetMapping(value = "/{id}")
    private MaterialDto getMaterialById(@PathVariable("id") Long id) {
        return materialService.getMaterialById(id);
    }

    @PostMapping
    private MaterialDto createMaterial(@RequestBody MaterialDto materialDto) {
        return materialService.addMaterial(materialDto);
    }

    @PutMapping(value = "/{id}")
    private MaterialDto updateMaterial(@PathVariable("id") Long id,
                                       @RequestBody MaterialDto materialDto) {
        return materialService.updateMaterial(materialDto.setId(id));
    }

    @DeleteMapping(value = "/{id}")
    private MaterialDto deleteMaterial(@PathVariable("id") Long id,
                                       @RequestBody MaterialDto materialDto) {
        return materialService.removeMaterial(materialDto.setId(id));
    }
}
