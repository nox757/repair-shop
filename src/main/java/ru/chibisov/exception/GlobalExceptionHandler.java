package ru.chibisov.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.chibisov.controller.dto.ResponseError;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestControllerAdvice(basePackages = "ru.chibisov.controller")
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class.getName());

    @Value("${message.exception.common.servererror}")
    private String commonServerErrorMessage;

    @Value("${system.name:repair-shop}")
    private String systemName;

    @ExceptionHandler(BadDataFieldException.class)
    public ResponseEntity<ResponseError> badDataFieldException(BadDataFieldException exception) {
        log.debug(exception.getLocalizedMessage(), exception);
        ResponseError error = new ResponseError(
                UUID.randomUUID(),
                "Bad Field Exception",
                exception.getLocalizedMessage(),
                systemName
        );
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadUrlRequestException.class)
    public ResponseEntity<ResponseError> requestHandlingNoHandlerFound(HttpServletRequest req, BadUrlRequestException exception) {
        String message = String.format("%s - from %s", exception.getLocalizedMessage(), req.getRequestURL().toString());
        log.debug(message, exception);
        ResponseError error = new ResponseError(
                UUID.randomUUID(),
                "Bad request",
                message,
                systemName
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ResponseError> objectNotFoundException(ObjectNotFoundException exception) {
        log.debug(exception.getLocalizedMessage(), exception);
        ResponseError error = new ResponseError(
                UUID.randomUUID(),
                "Object Not Found",
                exception.getLocalizedMessage(),
                systemName
        );
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ObjectDtoValidationException.class)
    public ResponseEntity<List<ResponseError>> objectValidationException(ObjectDtoValidationException exception) {
        log.debug(exception.getLocalizedMessage(), exception);
        List<ResponseError> errors = exception.getErrors().getAllErrors().stream()
                .map(ex ->
                        new ResponseError(UUID.randomUUID(),
                                ex.getCode(),
                                ex.getDefaultMessage(),
                                systemName
                        )
                ).collect(Collectors.toList());
        return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> otherException(Exception exception) {
        log.debug(exception.getLocalizedMessage(), exception);
        ResponseError error = new ResponseError(
                UUID.randomUUID(),
                "unknown",
                commonServerErrorMessage,
                systemName
        );
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
