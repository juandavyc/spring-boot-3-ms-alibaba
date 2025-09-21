package com.juandavyc.orders.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;


@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
@ToString
public class ErrorResponseDto {
    private Integer status;
    private String path;
    private HttpStatus code;
    private String message;
    private LocalDateTime timestamp;
}
