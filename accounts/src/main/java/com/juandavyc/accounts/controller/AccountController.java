package com.juandavyc.accounts.controller;

import com.juandavyc.accounts.constants.AccountResponseCode;
import com.juandavyc.accounts.dto.request.AccountRequestDto;
import com.juandavyc.accounts.dto.request.AccountUpdateDto;
import com.juandavyc.accounts.dto.response.AccountResponseDto;
import com.juandavyc.accounts.dto.response.PagedDataDto;
import com.juandavyc.accounts.dto.response.ResponseDto;
import com.juandavyc.accounts.helper.ResponseBuilder;
import com.juandavyc.accounts.service.IAccountService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
@Validated
public class AccountController {

    private final IAccountService accountService;

    @PostMapping
    public ResponseEntity<ResponseDto<UUID>> createAccount(
            @Valid @RequestBody AccountRequestDto accountRequestDto
    ) {

        UUID idAccount = accountService.createAccount(accountRequestDto);
        AccountResponseCode responseCode = AccountResponseCode.CREATED; //"string literals"

        return ResponseEntity
                .status(responseCode.getStatus())
                .header(HttpHeaders.LOCATION, String.format("/api/accounts/%s", idAccount))
                .body(ResponseBuilder.response(responseCode, idAccount));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseDto<AccountResponseDto>> fetchAccount(
            @PathVariable UUID id
    ) {

        AccountResponseDto account = accountService.fetchAccountById(id);
        AccountResponseCode responseCode = AccountResponseCode.SUCCESS;

        return ResponseEntity
                .status(responseCode.getStatus())
                .body(ResponseBuilder.response(responseCode, account));
    }

    @GetMapping(path = "/{id}/exists")
    public ResponseEntity<Boolean> existsAccountById(
            @PathVariable UUID id
    ){
        boolean exists = accountService.existById(id);
                return ResponseEntity
                .status(HttpStatus.OK)
                .body(exists);
    }


    @GetMapping(path = "/exists/by-email")
    public ResponseEntity<ResponseDto<Boolean>> existsByEmail(
            @RequestParam String email,
            @RequestParam(required = false) UUID id
    ) {
        AccountResponseCode response;
        boolean exists;
        if (id == null) {
            exists = accountService.existByEmail(email);
        } else {
            exists = accountService.existByEmailInUse(email, id);
        }
        response = (exists) ? AccountResponseCode.EXISTS : AccountResponseCode.AVAILABLE;

        return ResponseEntity
                .status(response.getStatus())
                .body(ResponseBuilder.response(response, exists));
    }

    @GetMapping(path = "/exists/by-username")
    public ResponseEntity<ResponseDto<Boolean>> existsByUsername(
            @RequestParam String username,
            @RequestParam(required = false) UUID id
    ) {
        AccountResponseCode response;
        boolean exists;
        if (id == null) {
            exists = accountService.existByUsername(username);
        } else {
            exists = accountService.existByUsernameInUse(username, id);
        }
        response = (exists) ? AccountResponseCode.EXISTS : AccountResponseCode.AVAILABLE;

        return ResponseEntity
                .status(response.getStatus())
                .body(ResponseBuilder.response(response, exists));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<PagedDataDto<AccountResponseDto>>> fetchAllAccounts(
            @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {

        PagedDataDto<AccountResponseDto> accountList = accountService.fetchAll(pageable);
        AccountResponseCode responseCode = AccountResponseCode.SUCCESS;

        return ResponseEntity
                .status(responseCode.getStatus())
                .body(ResponseBuilder.response(responseCode, accountList));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ResponseDto<AccountResponseDto>> updateAccount(
            @PathVariable UUID id,
            @Valid @RequestBody AccountUpdateDto accountUpdateDto
    ) {

        AccountResponseDto accountResponseDto = accountService.updateAccount(id, accountUpdateDto);
        AccountResponseCode responseCode = AccountResponseCode.SUCCESS;

        return ResponseEntity
                .status(responseCode.getStatus())
                .body(ResponseBuilder.response(
                        responseCode,
                        accountResponseDto)
                );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseDto<Map<String, Boolean>>> deleteAccount(
            @PathVariable UUID id
    ) {
        boolean isDeleted = accountService.deleteAccount(id);
        AccountResponseCode responseCode =
                (isDeleted) ? AccountResponseCode.SUCCESS : AccountResponseCode.DELETE_FAILED;

        return ResponseEntity
                .status(responseCode.getStatus())
                .body(ResponseBuilder.response(
                                responseCode,
                                Map.of("isDeleted", isDeleted)
                        )
                );
    }

}
