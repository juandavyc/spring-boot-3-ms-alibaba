package com.juandavyc.products.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ResponseDto<T> {

    private Integer code;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    public ResponseDto(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }
}
