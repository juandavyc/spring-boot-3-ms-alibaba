package com.juandavyc.inventory.exception;

import com.juandavyc.inventory.dto.response.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

//    @Value("${value.name}")
//    private final String name;
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {

        Map<String, String> validationErrors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            validationErrors.put(fieldName, errorMessage);
        });
        HttpStatus statusError = HttpStatus.BAD_REQUEST;

        return ResponseEntity
                .status(status)
                .body(new ErrorResponseDto(
                        statusError,
                        request.getDescription(false),
                        "Validation errors",
                        validationErrors
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(
            Exception exception,
            WebRequest webRequest
    ) {
        final var status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity
                .status(status)
                .body(new ErrorResponseDto(
                        status,
                        webRequest.getDescription(false),
                        exception.getMessage()
                ));
    }


    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceAlreadyExistsException(
            ResourceAlreadyExistsException resourceAlreadyExistsException,
            WebRequest webRequest
    ) {
        HttpStatus status = HttpStatus.CONFLICT;

        return ResponseEntity
                .status(status)
                .body(new ErrorResponseDto(
                        status,
                        webRequest.getDescription(false),
                        resourceAlreadyExistsException.getMessage()
                ));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(
            ResourceNotFoundException resourceNotFoundException,
            WebRequest webRequest
    ) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        return ResponseEntity
                .status(status)
                .body(new ErrorResponseDto(
                        status,
                        webRequest.getDescription(false),
                        resourceNotFoundException.getMessage()
                ));
    }

}
