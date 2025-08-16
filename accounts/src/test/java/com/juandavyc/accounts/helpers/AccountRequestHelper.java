package com.juandavyc.accounts.helpers;

import com.juandavyc.accounts.dto.request.AccountRequestDto;
import com.juandavyc.accounts.dto.response.AccountResponseDto;
import com.juandavyc.accounts.dto.response.ResponseDto;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public class AccountRequestHelper {

    public static <T> ResponseEntity <T> createAccount(
            TestRestTemplate testRestTemplate,
            String PATH,
            AccountRequestDto requestDto,
            ParameterizedTypeReference<T> type
    ) {
        return testRestTemplate.exchange(
                PATH,
                HttpMethod.POST,
                new HttpEntity<>(requestDto),
                type
        );

    }

    public static ResponseEntity<ResponseDto<AccountResponseDto>> fetchAccountById(
            TestRestTemplate testRestTemplate,
            String path
    ) {
        return testRestTemplate.exchange(
                path,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<>() {}
        );
    }
}
