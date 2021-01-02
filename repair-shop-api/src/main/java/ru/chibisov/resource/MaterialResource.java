package ru.chibisov.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
import ru.chibisov.resource.dto.ResponseError;
import ru.chibisov.resource.dto.search.MaterialSearchDto;

@RequestMapping("/api/v1/materials")
@Api(value = "API для работы с материалами")
public interface MaterialResource {

    @GetMapping(value = "{id}")
    @ApiOperation(value = "Детальная информация о материале")
    MaterialDto getMaterialById(@PathVariable("id") Long id);

    @GetMapping
    @ApiOperation(value = "Поиск по материалам")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Номер страницы (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Количество записей на странице"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Сортировка в формате: asc|desc. " +
                            "По умолчанию по возрастанию по имени (name). " +
                            "Сортировка по нескольким полям.")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Список найденных материалов, доступных вызывающей стороне",
                    response = MaterialSearchDto.class, responseContainer = "Page"),
            @ApiResponse(code = 400, message = "Непредвиденная ошибка", response = ResponseError.class),
            @ApiResponse(code = 401,
                    message = "Полномочия не подтверждены. Например, JWT невалиден, отсутствует, либо неверного формата",
                    response = ResponseError.class),
            @ApiResponse(code = 403, message = "Нет полномочий на выполнение запрашиваемой операции",
                    response = ResponseError.class)
    })
    Page<MaterialDto> getFilterUsers(@RequestBody MaterialSearchDto materialSearchDto,
                                     @PageableDefault(sort = {"name"}, direction = Sort.Direction.ASC) Pageable pageable);

    @PostMapping
    @ApiOperation(value = "Создание материала")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Материал успешно создан. Ссылка на вновь созданный материал в поле заголовка `Location`. Описание самого материала будет возвращено в теле ответа",
                    response = MaterialDto.class),
            @ApiResponse(code = 400, message = "Непредвиденная ошибка", response = ResponseError.class),
            @ApiResponse(code = 401,
                    message = "Полномочия не подтверждены. Например, JWT невалиден, отсутствует, либо неверного формата",
                    response = ResponseError.class),
            @ApiResponse(code = 403, message = "Нет полномочий на выполнение запрашиваемой операции",
                    response = ResponseError.class)
    })
    ResponseEntity<MaterialDto> createMaterial(@RequestBody MaterialDto materialDto, UriComponentsBuilder componentsBuilder);

    @PutMapping(value = "{id}")
    @ApiOperation(value = "Обновление материала")
    MaterialDto updateMaterial(@PathVariable("id") Long id, @RequestBody MaterialDto materialDto);

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "Удаление материала")
    void deleteMaterial(@PathVariable("id") Long id);

}
