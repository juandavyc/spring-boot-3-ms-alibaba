package com.juandavyc.accounts.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ResponseDto<T> {

    private Integer code;
    private HttpStatus status;
    private String message;
    private T data;
    private LocalDateTime timestamp;


    public ResponseDto(Integer code, HttpStatus status, String message, T data) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();

    }

}
