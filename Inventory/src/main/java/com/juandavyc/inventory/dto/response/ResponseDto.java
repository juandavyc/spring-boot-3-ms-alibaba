package com.juandavyc.inventory.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@ToString

public class ResponseDto<T> {
    private HttpStatus status;
    private Integer code;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    public ResponseDto(HttpStatus status, Integer code, String message, T data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }
}
