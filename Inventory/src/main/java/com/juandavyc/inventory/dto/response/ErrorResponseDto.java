package com.juandavyc.inventory.dto.response;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

@Data
@NoArgsConstructor

@ToString
public class ErrorResponseDto {

    private HttpStatus status;
    private Integer code;
    private String apiPath;
    private String message;
    private Map<String, String> errors;
    private LocalDateTime timestamp;

    public ErrorResponseDto(HttpStatus status, String apiPath, String message, Map<String, String> errors) {
        this.status = status;
        this.code = status.value();
        this.apiPath = apiPath;
        this.message = message;
        this.errors = errors;
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponseDto(HttpStatus status, String apiPath, String message) {
        this.status = status;
        this.code = status.value();
        this.apiPath = apiPath;
        this.message = message;
        this.errors = Collections.emptyMap();
        this.timestamp = LocalDateTime.now();
    }
}
