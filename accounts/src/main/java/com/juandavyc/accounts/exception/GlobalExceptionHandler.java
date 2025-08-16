package com.juandavyc.accounts.exception;

import com.juandavyc.accounts.dto.response.ErrorResponseDto;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        Map<String, String> validationErrors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (e, r) -> r
                ));
        HttpStatus statusError = HttpStatus.BAD_REQUEST;


        return ResponseEntity
                .status(statusError)
                .body(new ErrorResponseDto(
                        statusError.value(),
                        request.getDescription(false),
                        statusError,
                        "Validation error",
                        validationErrors
                ));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(
            Exception exception,
            WebRequest webRequest
    ) {
        final var status = HttpStatus.INTERNAL_SERVER_ERROR;
        return getErrorResponseDtoResponseEntity(exception, webRequest, status);
    }


    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceAlreadyExistsException(
            ResourceAlreadyExistsException exception,
            WebRequest webRequest
    ) {
        final var status = HttpStatus.CONFLICT;
        return getErrorResponseDtoResponseEntity(exception, webRequest, status);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(
            ResourceNotFoundException exception,
            WebRequest webRequest
    ) {
        final var status = HttpStatus.NOT_FOUND;
        return getErrorResponseDtoResponseEntity(exception, webRequest, status);
    }


    private static ResponseEntity<ErrorResponseDto> getErrorResponseDtoResponseEntity(
            Throwable exception,
            WebRequest webRequest,
            HttpStatus status
    ) {
        return ResponseEntity
                .status(status)
                .body(new ErrorResponseDto(
                        status.value(),
                        webRequest.getDescription(false),
                        status,
                        exception.getMessage()
                ));
    }

}
