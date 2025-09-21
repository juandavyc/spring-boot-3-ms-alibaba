package com.juandavyc.accounts.helper;

import com.juandavyc.accounts.constants.AccountResponseCode;
import com.juandavyc.accounts.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;

public class ResponseBuilder {

    public static <T> ResponseDto<T> response(AccountResponseCode responseCode, T data){
        return new ResponseDto<>(
                responseCode.getStatus().value(),
                responseCode.getStatus(),
                responseCode.getMessage(),
                data
        );
    }



}
