package com.juandavyc.accounts.service;

import com.juandavyc.accounts.dto.request.AccountRequestDto;
import com.juandavyc.accounts.dto.request.AccountUpdateDto;
import com.juandavyc.accounts.dto.response.AccountResponseDto;
import com.juandavyc.accounts.dto.response.PagedDataDto;
import org.springframework.data.domain.Pageable;


public interface IAccountService {

    Long createAccount(AccountRequestDto accountRequestDto);
    AccountResponseDto fetchAccountById(Long id);
    PagedDataDto<AccountResponseDto> fetchAll(Pageable pageable);
    AccountResponseDto updateAccount(Long id, AccountUpdateDto accountUpdateDto);
    boolean deleteAccount(Long id);

}
