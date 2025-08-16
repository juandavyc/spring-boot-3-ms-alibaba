package com.juandavyc.accounts.helper;

import com.juandavyc.accounts.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;

public class ResponseBuilder {

    public static <T> ResponseDto<T> response(HttpStatus status, String message, T data){
        return new ResponseDto<>(
                status.value(),
                status,
                message,
                data
        );
    }



}
