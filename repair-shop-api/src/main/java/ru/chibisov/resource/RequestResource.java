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
import ru.chibisov.resource.dto.RequestDto;
import ru.chibisov.resource.dto.search.RequestSearchDto;

@RequestMapping("/api/v1/requests")
@Api(value = "API для работы с заявками")
public interface RequestResource {

    @GetMapping(value = "{id}")
    @ApiOperation(value = "Детальная информация о заявке")
    RequestDto getRequestById(@PathVariable("id") Long id);

    @GetMapping
    @ApiOperation(value = "Поиск по заявкам")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Номер страницы (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Количество записей на странице"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Сортировка в формате: asc|desc. " +
                            "По умолчанию по возрастанию по сумме (amount). " +
                            "Сортировка по нескольким полям.")
    })
    Page<RequestDto> getFilterUsers(@RequestBody RequestSearchDto requestSearchDto,
                                    @PageableDefault(sort = {"amount"}, direction = Sort.Direction.ASC) Pageable pageable);

    @PostMapping
    @ApiOperation(value = "Создание заявки")
    ResponseEntity<RequestDto> createRequest(@RequestBody RequestDto requestDto, UriComponentsBuilder componentsBuilder);

    @PutMapping(value = "{id}")
    @ApiOperation(value = "Обновление заявки")
    RequestDto updateRequest(@PathVariable("id") Long id, @RequestBody RequestDto requestDto);

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "Удаление заявки")
    void deleteRequest(@PathVariable("id") Long id);


}
