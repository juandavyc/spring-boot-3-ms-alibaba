package com.juandavyc.orders.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.juandavyc.orders.dto.response.ErrorResponseDto;
import com.juandavyc.orders.dto.response.ErrorsResponseDto;
import com.juandavyc.orders.entity.enums.OrderStatus;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {




    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        Map<String, String> errors = new LinkedHashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(field, errorMessage);
        });

        return ResponseEntity
                .status(status)
                .body(ErrorsResponseDto
                        .builder()
                        .status(status.value())
                        .path(request.getDescription(false))
                        .code(HttpStatus.valueOf(status.value()))
                        .message("Validation errors")
                        .errors(errors)
                        .timestamp(LocalDateTime.now())
                        .build());

    }




    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(
            Exception exception,
            WebRequest webRequest
    ) {
        final var status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity
                .status(status)
                .body(ErrorResponseDto
                        .builder()
                        .status(status.value())
                        .path(webRequest.getDescription(false))
                        .code(status)
                        .message(exception.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }


    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceAlreadyExistsException(
            ResourceAlreadyExistsException exception,
            WebRequest webRequest
    ) {
        HttpStatus status = HttpStatus.CONFLICT;

        return ResponseEntity
                .status(status)
                .body(ErrorResponseDto
                        .builder()
                        .status(status.value())
                        .path(webRequest.getDescription(false))
                        .code(status)
                        .message(exception.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(
            ResourceNotFoundException exception,
            WebRequest webRequest
    ) {
        final var status = HttpStatus.NOT_FOUND;
        return ResponseEntity
                .status(status)
                .body(ErrorResponseDto
                        .builder()
                        .status(status.value())
                        .path(webRequest.getDescription(false))
                        .code(status)
                        .message(exception.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }

}
