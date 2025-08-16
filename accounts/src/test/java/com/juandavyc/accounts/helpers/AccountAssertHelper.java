package com.juandavyc.accounts.helpers;

import com.juandavyc.accounts.dto.response.AccountResponseDto;
import com.juandavyc.accounts.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountAssertHelper {

    public static void assertAccountCreated(
            ResponseEntity<ResponseDto<Long>> response
    ) {

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody())
                .isNotNull();
        assertThat(response.getBody().getMessage())
                .isEqualToIgnoringCase("Account created successfully");
        assertThat(response.getBody().getData())
                .isNotNull()
                .isPositive();

    }

    public static ResponseDto<AccountResponseDto> assertAccountFetchedById(
            ResponseEntity<ResponseDto<AccountResponseDto>> response
    ){
        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        assertThat(response.getBody())
                .isNotNull();

        assertThat(response.getBody().getData())
                .isNotNull();

        return response.getBody();
    }



}
