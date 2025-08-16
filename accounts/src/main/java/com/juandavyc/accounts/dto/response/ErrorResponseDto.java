package com.juandavyc.accounts.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

@Data
@NoArgsConstructor

public class ErrorResponseDto {

    private Integer status;
    private String path;
    private HttpStatus code;
    private String message;
    private Map<String, String> errors;
    private LocalDateTime timestamp;

    public ErrorResponseDto(Integer status, String path, HttpStatus code, String message) {
        this.status = status;
        this.path = path;
        this.code = code;
        this.message = message;
        this.errors = Collections.emptyMap();
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponseDto(Integer status, String path, HttpStatus code, String message, Map<String, String> errors) {
        this.status = status;
        this.path = path;
        this.code = code;
        this.message = message;
        this.errors = errors;
        this.timestamp = LocalDateTime.now();
    }
}
