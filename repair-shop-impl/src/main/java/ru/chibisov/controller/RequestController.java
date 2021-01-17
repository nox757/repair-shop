package ru.chibisov.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import ru.chibisov.resource.RequestResource;
import ru.chibisov.resource.dto.RequestDto;
import ru.chibisov.resource.dto.search.RequestSearchDto;
import ru.chibisov.service.RequestService;
import ru.chibisov.validator.RequestDtoValidator;

import java.net.URI;

@RestController
public class RequestController implements RequestResource {

    private static final Logger log = LoggerFactory.getLogger(RequestController.class);

    private RequestService requestService;
    private RequestDtoValidator requestDtoValidator;
    private ObjectMapper jsonMapper;

    public RequestController(RequestService requestService, RequestDtoValidator requestDtoValidator, ObjectMapper mapper) {
        this.requestService = requestService;
        this.requestDtoValidator = requestDtoValidator;
        this.jsonMapper = mapper;
    }

    @Override
    public RequestDto getRequestById(Long id) {
        log.debug("getRequestById with {} - start", id);
        RequestDto result = requestService.getRequestById(id);
        log.debug("getRequestById end with result {}", result);
        return result;
    }

    @Override
    public Page<RequestDto> getFilterRequests(RequestSearchDto requestSearchDto, Pageable pageable) {
        log.debug("getFilterRequests with {} - start", requestSearchDto);
        Page<RequestDto> result = requestService.getRequests(requestSearchDto, pageable);
        log.debug("getFilterRequests end with result {}", result.getContent());
        return result;
    }

    @Override
    @Audit(AuditCode.CREATE_REQUEST)
    public ResponseEntity<RequestDto> createRequest(@Validated RequestDto requestDto, UriComponentsBuilder componentsBuilder) {
        log.debug("createRequest with {} - start", requestDto);
        RequestDto result = requestService.addRequest(requestDto);
        URI uri = componentsBuilder.path("/api/v1/requests/" + result.getId()).buildAndExpand(result).toUri();
        log.debug("createRequest end with result {}", result);
        return ResponseEntity.created(uri).body(result);
    }

    @Override
    @Audit(AuditCode.UPDATE_REQUEST)
    public RequestDto updateRequest(Long id, @Validated RequestDto requestDto) {
        log.debug("updateRequest with {} - start", requestDto);
        if (!requestDto.getId().equals(id)) {
            throw new BadUrlRequestException("Path and body ID must be equal");
        }
        RequestDto result = requestService.updateRequest(requestDto.setId(id));
        log.debug("updateRequest end with result {}", result);
        return result;
    }

    @Override
    @Audit(AuditCode.DELETE_REQUEST)
    public void deleteRequest(Long id) {
        log.debug("deleteRequest with {} - start", id);
        requestService.removeRequestById(id);
        log.debug("deleteRequest end with result {}", id);
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
