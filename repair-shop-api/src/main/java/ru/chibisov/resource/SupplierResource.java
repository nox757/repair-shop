package ru.chibisov.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
@Api(value = "API для работы с поставщиками")
public interface SupplierResource {

    @GetMapping(value = "{id}")
    @ApiOperation(value = "Детальная информация о поставщике")
    SupplierDto getSupplierById(@PathVariable("id") Long id);

    @GetMapping
    @ApiOperation(value = "Поиск по поставщикам")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Номер страницы (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Количество записей на странице"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Сортировка в формате: asc|desc. " +
                            "По умолчанию по возрастанию по названию организации (orgName). " +
                            "Сортировка по нескольким полям.")
    })
    Page<SupplierDto> getFilterSupplier(@RequestBody SupplierSearchDto supplierSearchDto,
                                        @PageableDefault(sort = {"orgName"}, direction = Sort.Direction.ASC) Pageable pageable);

    @PostMapping
    @ApiOperation(value = "Создание поставщика")
    ResponseEntity<SupplierDto> createSupplier(@RequestBody SupplierDto supplierDto, UriComponentsBuilder componentsBuilder);

    @PutMapping(value = "{id}")
    @ApiOperation(value = "Обновление поставщика")
    SupplierDto updateSupplier(@PathVariable("id") Long id, @RequestBody SupplierDto supplierDto);

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "Удаление поставщика")
    void deleteSupplier(@PathVariable("id") Long id);

    @GetMapping(value = "{id}/materials")
    List<MaterialDto> getMaterialsBySupplierId(@PathVariable("id") Long id);


}
