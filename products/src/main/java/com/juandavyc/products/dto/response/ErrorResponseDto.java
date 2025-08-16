package com.juandavyc.products.dto.response;

import lombok.Data;


import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

@Data

public class ErrorResponseDto {

    private Integer code;
    private String path;
    private String message;
    private Map<String, String> errors;
    private LocalDateTime timestamp;


    public ErrorResponseDto(Integer code, String path, String message) {
        this.code = code;
        this.path = path;
        this.message = message;
        this.errors = Collections.emptyMap();
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponseDto(Integer code, String path, String message, Map<String, String> errors) {
        this.code = code;
        this.path = path;
        this.message = message;
        this.errors = errors;
        this.timestamp = LocalDateTime.now();
    }
}
