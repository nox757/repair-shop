package ru.chibisov.resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public interface RequestResource {

    @GetMapping(value = "{id}")
    RequestDto getRequestById(@PathVariable("id") Long id);

    @GetMapping
    Page<RequestDto> getFilterUsers(@RequestBody RequestSearchDto requestSearchDto, Pageable pageable);

    @PostMapping
    ResponseEntity<RequestDto> createRequest(@RequestBody RequestDto requestDto, UriComponentsBuilder componentsBuilder);

    @PutMapping(value = "{id}")
    RequestDto updateRequest(@PathVariable("id") Long id, @RequestBody RequestDto requestDto);

    @DeleteMapping(value = "{id}")
    void deleteRequest(@PathVariable("id") Long id);


}
