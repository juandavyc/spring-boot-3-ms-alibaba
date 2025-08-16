package com.juandavyc.accounts.mapper;

import com.juandavyc.accounts.dto.request.AccountRequestDto;
import com.juandavyc.accounts.dto.request.AccountUpdateDto;
import com.juandavyc.accounts.dto.response.AccountResponseDto;
import com.juandavyc.accounts.dto.response.PagedDataDto;
import com.juandavyc.accounts.entity.Account;

import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;


public class AccountMapper {

//    public static AccountResponseDto toDto(Account account) {
//        return new AccountResponseDto();
//    }

    public static Account toAccount(AccountRequestDto accountRequestDto, Account account) {

        account.setUsername(accountRequestDto.getUsername());
        account.setEmail(accountRequestDto.getEmail());
        account.setDeleted(false);
        account.setCreatedAt(LocalDateTime.now());
        account.setCreatedBy("SYSTEM");
        return account;
    }

    public static AccountResponseDto toDto(Account account) {

        return new AccountResponseDto(
                account.getId(),
                account.getUsername(),
                account.getEmail(),
                account.getDeleted(),
                account.getCreatedAt(),
                account.getUpdatedAt()
        );
    }

    public static Account accountUpdateToAccount(AccountUpdateDto accountUpdateDto, Account account) {

        account.setUsername(accountUpdateDto.getUsername());
        account.setEmail(accountUpdateDto.getEmail());

        account.setUpdatedBy("SYSTEM");
        account.setUpdatedAt(LocalDateTime.now());

        return account;
    }

    public static PagedDataDto<AccountResponseDto> toPagedDto(Page<Account> accounts) {
        List<AccountResponseDto> accountList = accounts.getContent().stream()
                .map(AccountMapper::toDto)
                .toList();
        return new PagedDataDto<>(
                accountList,
                accounts.getNumber(),
                accounts.getSize(),
                accounts.getTotalElements(),
                accounts.getTotalPages(),
                accounts.isFirst(),
                accounts.isLast(),
                accounts.isEmpty(),
                accounts.hasPrevious(),
                accounts.hasNext()
        );
    }
}
