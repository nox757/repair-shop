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
import ru.chibisov.controller.dto.RequestDto;
import ru.chibisov.controller.dto.UserDto;
import ru.chibisov.controller.dto.search.RequestSearchDto;
import ru.chibisov.controller.dto.search.UserSearchDto;
import ru.chibisov.exception.BadUrlRequestException;
import ru.chibisov.model.Request;
import ru.chibisov.service.RequestService;
import ru.chibisov.validator.RequestDtoValidator;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/requests")
public class RequestController {

    private RequestService requestService;
    private RequestDtoValidator requestDtoValidator;

    public RequestController(RequestService requestService, RequestDtoValidator requestDtoValidator) {
        this.requestService = requestService;
        this.requestDtoValidator = requestDtoValidator;
    }

    @GetMapping(value = "{id}")
    private RequestDto getRequestById(@PathVariable("id") Long id) {
        return requestService.getRequestById(id);
    }

    @GetMapping
    public Page<RequestDto> getFilterRequests(@RequestBody RequestSearchDto requestSearchDto, Pageable pageable) {
        return requestService.getRequests(requestSearchDto, pageable);
    }

    @PostMapping
    private ResponseEntity<RequestDto> createRequest(@Validated @RequestBody RequestDto requestDto, UriComponentsBuilder componentsBuilder) {
        RequestDto result = requestService.addRequest(requestDto);
        URI uri = componentsBuilder.path("/api/v1/requests/" + result.getId()).buildAndExpand(result).toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @PutMapping(value = "{id}")
    private RequestDto updateRequest(@PathVariable("id") Long id,
                                     @Validated @RequestBody RequestDto requestDto) {
        if (!requestDto.getId().equals(id)) {
            throw new BadUrlRequestException("Path and body ID must be equal");
        }
        return requestService.updateRequest(requestDto.setId(id));
    }

    @DeleteMapping(value = "{id}")
    private void deleteRequest(@PathVariable("id") Long id) {
        requestService.removeRequestById(id);
    }

    @ModelAttribute
    public RequestDto requestDto() {
        return new RequestDto();
    }

    @InitBinder(value = "requestDto")
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(requestDtoValidator);
    }
}
