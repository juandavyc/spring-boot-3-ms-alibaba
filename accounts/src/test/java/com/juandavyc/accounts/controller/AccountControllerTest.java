package com.juandavyc.accounts.controller;

import com.juandavyc.accounts.dto.request.AccountRequestDto;
import com.juandavyc.accounts.dto.request.AccountUpdateDto;
import com.juandavyc.accounts.dto.response.AccountResponseDto;
import com.juandavyc.accounts.dto.response.ErrorResponseDto;
import com.juandavyc.accounts.dto.response.PagedDataDto;
import com.juandavyc.accounts.dto.response.ResponseDto;
import com.juandavyc.accounts.helpers.AccountAssertHelper;
import com.juandavyc.accounts.helpers.AccountRequestHelper;

import org.apiguardian.api.API;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.test.context.TestPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
        "build.version=1.0.0-test",
        "spring.application.name=account"
})
class AccountControllerTest {

    public static final String API_ACCOUNTS_PATH = "/api/accounts";

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void shouldCreateAccount() {

        AccountRequestDto requestDto = new AccountRequestDto(
                UUID.randomUUID() + "_test",
                UUID.randomUUID() + "@test.com"
        );

        ResponseEntity<ResponseDto<Long>> createAccountResponse =
                AccountRequestHelper.createAccount(
                        testRestTemplate,
                        API_ACCOUNTS_PATH,
                        requestDto,
                        new ParameterizedTypeReference<>() {
                        }
                );

        AccountAssertHelper.assertAccountCreated(createAccountResponse);

        assertThat(createAccountResponse.getHeaders().getLocation())
                .isNotNull();

        String createdAccountUrl = createAccountResponse.getHeaders().getLocation().toString();

        ResponseEntity<ResponseDto<AccountResponseDto>> fetchAccountResponse =
                AccountRequestHelper.fetchAccountById(
                        testRestTemplate,
                        createdAccountUrl
                );

        ResponseDto<AccountResponseDto> responseFetchAccountBody =
                AccountAssertHelper.assertAccountFetchedById(fetchAccountResponse);

        assertThat(responseFetchAccountBody.getData())
                .usingRecursiveComparison()
                .comparingOnlyFields("username", "email")
                .isEqualTo(requestDto);

    }

    @Test
    void shouldReturnValidationErrorWhenGivenWrongData() {
        AccountRequestDto requestDto = new AccountRequestDto(
                "",
                "aa@a"
        );
        ResponseEntity<ErrorResponseDto> createAccountResponse =
                AccountRequestHelper.createAccount(
                        testRestTemplate,
                        API_ACCOUNTS_PATH,
                        requestDto,
                        new ParameterizedTypeReference<>() {
                        }
                );

        assertThat(createAccountResponse.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(createAccountResponse.getBody())
                .isNotNull();

        assertThat(createAccountResponse.getBody().getErrors())
                .isNotNull()
                .containsKeys("username", "email");

        assertThat(createAccountResponse.getBody().getErrors().get("username"))
                .contains("Username cannot be null or empty");

        assertThat(createAccountResponse.getBody().getErrors().get("email"))
                .contains("Invalid email format");

    }

    @Test
    void shouldReturnAccounts() {

        String apiPath = API_ACCOUNTS_PATH + "?page=0&size=5&sort=username,asc";

        ResponseEntity<ResponseDto<PagedDataDto<AccountResponseDto>>> fetchAllAccountsResponse
                = testRestTemplate.exchange(
                apiPath,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<>() {
                }
        );

        assertThat(fetchAllAccountsResponse.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(fetchAllAccountsResponse.getBody())
                .isNotNull();

        ResponseDto<PagedDataDto<AccountResponseDto>> responseBody =
                fetchAllAccountsResponse.getBody();

        assertThat(responseBody.getData())
                .isNotNull();

        PagedDataDto<AccountResponseDto> pagedData = responseBody.getData();

        assertThat(pagedData.getContent().size())
                .isGreaterThan(0);

        assertThat(pagedData.getContent())
                .hasSizeLessThanOrEqualTo(5);

        List<String> usernames = pagedData.getContent().stream()
                .map(AccountResponseDto::username)
                .toList();

        assertThat(usernames).isSorted();

    }

    @Test
    void shouldUpdateAccount() {
        long id = 1L;
        AccountUpdateDto requestDto = new AccountUpdateDto(
                UUID.randomUUID() + "_test",
                UUID.randomUUID() + "@test.com"
        );

        ResponseEntity<ResponseDto<AccountResponseDto>> updateAccountResponse =
                testRestTemplate.exchange(
                        API_ACCOUNTS_PATH + "/" + id,
                        HttpMethod.PUT,
                        new HttpEntity<>(requestDto),
                        new ParameterizedTypeReference<>() {
                        }
                );

        assertThat(updateAccountResponse.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        assertThat(updateAccountResponse.getBody())
                .isNotNull();


        ResponseEntity<ResponseDto<AccountResponseDto>> fetchAccountResponse =
                AccountRequestHelper.fetchAccountById(
                        testRestTemplate,
                        API_ACCOUNTS_PATH + "/" + id
                );

        ResponseDto<AccountResponseDto> accountById =
                AccountAssertHelper.assertAccountFetchedById(fetchAccountResponse);


        assertThat(accountById.getData().id())
                .isEqualTo(id);

        assertThat(accountById.getData())
                .usingRecursiveComparison()
                .comparingOnlyFields("username", "email")
                .isEqualTo(requestDto);


    }

    @Test
    void shouldReturnNotFoundWhenGivenInvalidIdWhileUpdateAccount() {
        long id = 9999L;
        AccountUpdateDto requestDto = new AccountUpdateDto(
                UUID.randomUUID() + "_test",
                UUID.randomUUID() + "@test.com"
        );

        ResponseEntity<ErrorResponseDto> updateAccountResponse =
                testRestTemplate.exchange(
                        API_ACCOUNTS_PATH + "/" + id,
                        HttpMethod.PUT,
                        new HttpEntity<>(requestDto),
                        new ParameterizedTypeReference<>() {
                        }
                );

        assertThat(updateAccountResponse.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);

        assertThat(updateAccountResponse.getBody())
                .isNotNull();

        assertThat(updateAccountResponse.getBody().getMessage())
                .contains("Account",String.valueOf(id));

    }


}