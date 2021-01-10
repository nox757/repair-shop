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
import ru.chibisov.resource.RequestResource;
import ru.chibisov.resource.dto.RequestDto;
import ru.chibisov.resource.dto.search.RequestSearchDto;
import ru.chibisov.service.RequestService;
import ru.chibisov.validator.RequestDtoValidator;

import java.net.URI;

@RestController
public class RequestController implements RequestResource {

    private RequestService requestService;
    private RequestDtoValidator requestDtoValidator;

    public RequestController(RequestService requestService, RequestDtoValidator requestDtoValidator) {
        this.requestService = requestService;
        this.requestDtoValidator = requestDtoValidator;
    }

    @Override
    public RequestDto getRequestById(Long id) {
        return requestService.getRequestById(id);
    }

    @Override
    public Page<RequestDto> getFilterRequests(RequestSearchDto requestSearchDto, Pageable pageable) {
        return requestService.getRequests(requestSearchDto, pageable);
    }

    @Override
    public ResponseEntity<RequestDto> createRequest(@Validated RequestDto requestDto, UriComponentsBuilder componentsBuilder) {
        RequestDto result = requestService.addRequest(requestDto);
        URI uri = componentsBuilder.path("/api/v1/requests/" + result.getId()).buildAndExpand(result).toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @Override
    public RequestDto updateRequest(Long id, @Validated RequestDto requestDto) {
        if (!requestDto.getId().equals(id)) {
            throw new BadUrlRequestException("Path and body ID must be equal");
        }
        return requestService.updateRequest(requestDto.setId(id));
    }

    @Override
    public void deleteRequest(Long id) {
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
