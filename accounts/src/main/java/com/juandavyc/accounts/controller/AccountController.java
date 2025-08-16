package com.juandavyc.accounts.controller;

import com.juandavyc.accounts.constants.AccountResponseCode;
import com.juandavyc.accounts.dto.request.AccountRequestDto;
import com.juandavyc.accounts.dto.request.AccountUpdateDto;
import com.juandavyc.accounts.dto.response.AccountResponseDto;
import com.juandavyc.accounts.dto.response.PagedDataDto;
import com.juandavyc.accounts.dto.response.PagedResponseDto;
import com.juandavyc.accounts.dto.response.ResponseDto;
import com.juandavyc.accounts.helper.ResponseBuilder;
import com.juandavyc.accounts.service.IAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/accounts")
@RequiredArgsConstructor
@Validated
public class AccountController {

    private final IAccountService iAccountService;

    @PostMapping()
    public ResponseEntity<ResponseDto<Long>> createAccount(
            @Valid @RequestBody AccountRequestDto accountRequestDto
    ) {

        Long idAccount = iAccountService.createAccount(accountRequestDto);
        AccountResponseCode responseCode = AccountResponseCode.CREATED; //"string literals"

        return ResponseEntity
                .status(responseCode.getStatus())
                .header(HttpHeaders.LOCATION, String.format("/api/accounts/%s", idAccount))
                .body(ResponseBuilder.response(responseCode.getStatus(), responseCode.getMessage(), idAccount));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseDto<AccountResponseDto>> fetchAccount(
            @PathVariable Long id
    ) {

        AccountResponseDto account = iAccountService.fetchAccountById(id);
        AccountResponseCode responseCode = AccountResponseCode.SUCCESS;

        return ResponseEntity
                .status(responseCode.getStatus())
                .body(ResponseBuilder.response(responseCode.getStatus(), responseCode.getMessage(), account));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<PagedDataDto<AccountResponseDto>>> fetchAllAccounts(
            @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {

        PagedDataDto<AccountResponseDto> accountList = iAccountService.fetchAll(pageable);
        AccountResponseCode responseCode = AccountResponseCode.SUCCESS;

        return ResponseEntity
                .status(responseCode.getStatus())
                .body(ResponseBuilder.response(
                                responseCode.getStatus(),
                                responseCode.getMessage(),
                                accountList
                        )
                );
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ResponseDto<AccountResponseDto>> updateAccount(
            @PathVariable Long id,
            @Valid @RequestBody AccountUpdateDto accountUpdateDto
    ) {

        AccountResponseDto accountResponseDto = iAccountService.updateAccount(id, accountUpdateDto);
        AccountResponseCode responseCode = AccountResponseCode.SUCCESS;

        return ResponseEntity
                .status(responseCode.getStatus())
                .body(ResponseBuilder.response(
                        responseCode.getStatus(),
                        responseCode.getMessage(),
                        accountResponseDto)
                );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseDto<String>> deleteAccount(
            @PathVariable Long id
    ) {

        boolean isDeleted = iAccountService.deleteAccount(id);
        if (isDeleted) {

            AccountResponseCode responseCode = AccountResponseCode.SUCCESS;
            return ResponseEntity
                    .status(responseCode.getStatus())
                    .body(ResponseBuilder.response(
                            responseCode.getStatus(),
                            responseCode.getMessage(),
                            String.format("Account with id '%d' was successfully deleted", id))
                    );
        } else {

            AccountResponseCode responseCode = AccountResponseCode.DELETE_FAILED;
            return ResponseEntity
                    .status(responseCode.getStatus())
                    .body(ResponseBuilder.response(
                            responseCode.getStatus(),
                            responseCode.getMessage(),
                            String.format("Account with id '%d' cannot be deleted", id))
                    );
        }

    }

}
