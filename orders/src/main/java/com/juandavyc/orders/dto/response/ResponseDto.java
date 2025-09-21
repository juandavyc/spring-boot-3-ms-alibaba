package com.juandavyc.orders.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@ToString

public class ResponseDto<T> {
    private Integer status;
    private Integer code;
    private String message;
    private T data;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    public ResponseDto() {
        this.timestamp = LocalDateTime.now();
    }

    public ResponseDto(HttpStatus status, Integer code, String message, T data) {
        this.status = status.value();
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }
}
