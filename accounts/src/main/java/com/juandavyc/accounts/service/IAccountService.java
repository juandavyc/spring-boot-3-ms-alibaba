package com.juandavyc.accounts.service;

import com.juandavyc.accounts.dto.request.AccountRequestDto;
import com.juandavyc.accounts.dto.request.AccountUpdateDto;
import com.juandavyc.accounts.dto.response.AccountResponseDto;
import com.juandavyc.accounts.dto.response.PagedDataDto;
import org.springframework.data.domain.Pageable;

import java.util.UUID;


public interface IAccountService {

    UUID createAccount(AccountRequestDto accountRequestDto);
    AccountResponseDto fetchAccountById(UUID id);
    PagedDataDto<AccountResponseDto> fetchAll(Pageable pageable);
    AccountResponseDto updateAccount(UUID id, AccountUpdateDto accountUpdateDto);
    boolean deleteAccount(UUID id);

    boolean existByEmail(String email);
    boolean existByUsername(String username);

    boolean existByEmailInUse(String email, UUID id);
    boolean existByUsernameInUse(String username, UUID id);

    boolean existById(UUID id);
}
