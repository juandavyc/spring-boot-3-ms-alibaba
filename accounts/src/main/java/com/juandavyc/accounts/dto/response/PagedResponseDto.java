package com.juandavyc.accounts.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class PagedResponseDto<T> {

    private Integer code;
    private HttpStatus status;
    private PagedDataDto<T> data;
    private LocalDateTime timestamp;

    public PagedResponseDto(Integer code, HttpStatus status, PagedDataDto<T> data) {
        this.code = code;
        this.status = status;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

}
