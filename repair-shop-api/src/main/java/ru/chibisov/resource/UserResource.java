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
import ru.chibisov.resource.dto.UserDto;
import ru.chibisov.resource.dto.search.UserSearchDto;

@RequestMapping("/api/v1/users")
@Api(value = "API для работы с пользователями")
public interface UserResource {

    @GetMapping(value = "{id}")
    @ApiOperation(value = "Детальная информация о пользователе")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Найденный пользователь"),
            @ApiResponse(code = 400, message = "Непредвиденная ошибка", response = ResponseError.class),
    })
    UserDto getUserById(@PathVariable("id") Long id);

    @GetMapping
    @ApiOperation(value = "Поиск по пользователям")
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
            @ApiResponse(code = 200, message = "Список найденных пользователей, доступных вызывающей стороне",
                    response = UserSearchDto.class, responseContainer = "Page"),
            @ApiResponse(code = 400, message = "Непредвиденная ошибка", response = ResponseError.class)
    })
    Page<UserDto> getFilterUsers(@RequestBody UserSearchDto userSearchDto,
                                 @PageableDefault(sort = {"name"}, direction = Sort.Direction.ASC) Pageable pageable);

    @PostMapping
    @ApiOperation(value = "Создание пользователя")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Пользователь успешно создан. Ссылка на вновь созданного пользователя в поле заголовка `Location`. Описание самого пользователя будет возвращено в теле ответа",
                    response = MaterialDto.class),
            @ApiResponse(code = 400, message = "Непредвиденная ошибка", response = ResponseError.class)
    })
    ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto, UriComponentsBuilder componentsBuilder);

    @PutMapping(value = "{id}")
    @ApiOperation(value = "Обновление пользователя")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Пользователь успешно обновлен"),
            @ApiResponse(code = 400, message = "Непредвиденная ошибка", response = ResponseError.class),
    })
    UserDto updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto);

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "Удаление пользователя")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Пользователь успешно удален"),
            @ApiResponse(code = 400, message = "Непредвиденная ошибка", response = ResponseError.class),
    })
    void deleteUser(@PathVariable("id") Long id);

}
