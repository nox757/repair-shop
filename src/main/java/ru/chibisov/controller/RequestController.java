package ru.chibisov.controller;

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
import ru.chibisov.controller.dto.RequestDto;
import ru.chibisov.service.RequestService;
import ru.chibisov.validator.RequestDtoValidator;

import java.util.List;

@RestController
@RequestMapping("/request")
public class RequestController {

    private RequestService requestService;
    private RequestDtoValidator requestDtoValidator;

    public RequestController(RequestService requestService, RequestDtoValidator requestDtoValidator) {
        this.requestService = requestService;
        this.requestDtoValidator = requestDtoValidator;
    }

    @GetMapping
    private List<RequestDto> getRequests() {
        return requestService.getAllRequests();
    }

    @GetMapping(value = "/{id}")
    private RequestDto getRequestById(@PathVariable("id") Long id) {
        return requestService.getRequestById(id);
    }

    @PostMapping
    private RequestDto createRequest(@Validated @RequestBody RequestDto requestDto) {
        return requestService.addRequest(requestDto);
    }

    @PutMapping(value = "/{id}")
    private RequestDto updateRequest(@PathVariable("id") Long id,
                                     @Validated @RequestBody RequestDto requestDto) {
        return requestService.updateRequest(requestDto.setId(id));
    }

    @DeleteMapping(value = "/{id}")
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
