package com.juandavyc.accounts.service.impl;

import com.juandavyc.accounts.dto.request.AccountRequestDto;
import com.juandavyc.accounts.dto.request.AccountUpdateDto;
import com.juandavyc.accounts.dto.response.AccountResponseDto;
import com.juandavyc.accounts.dto.response.PagedDataDto;
import com.juandavyc.accounts.entity.Account;
import com.juandavyc.accounts.exception.ResourceAlreadyExistsException;
import com.juandavyc.accounts.exception.ResourceNotFoundException;
import com.juandavyc.accounts.mapper.AccountMapper;
import com.juandavyc.accounts.repository.AccountRepository;
import com.juandavyc.accounts.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final AccountRepository accountRepository;

    @Override
    public Long createAccount(AccountRequestDto accountRequestDto) {

        final var username = accountRequestDto.getUsername();
        final var email = accountRequestDto.getEmail();

        Optional<Account> accountByUsername = accountRepository.findByUsername(username);
        if (accountByUsername.isPresent())
            return tryRestoreIfExist(accountByUsername,accountRequestDto,"Username",username);
        Optional<Account> accountByEmail = accountRepository.findByEmail(email);
        if (accountByEmail.isPresent())
            return tryRestoreIfExist(accountByEmail,accountRequestDto,"Email",email);


        Account account = new Account();
        AccountMapper.toAccount(accountRequestDto, account);
        Long idSavedAccount = accountRepository.save(account).getId();
        return idSavedAccount;
    }

    private Long tryRestoreIfExist(
            Optional<Account> optionalAccount,
            AccountRequestDto accountRequestDto,
            String field,
            String value
    ) {
        Account account = optionalAccount.get();

        if (account.getDeleted()) {
            account.setDeleted(false);
            AccountMapper.toAccount(accountRequestDto, account);
            return accountRepository.save(account).getId();
        } else {
            throw new ResourceAlreadyExistsException("Account", field, value);
        }

    }


    @Override
    public AccountResponseDto fetchAccountById(Long id) {

        Account account = accountRepository.findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "id", id.toString()));
        AccountResponseDto accountResponseDto = AccountMapper.toDto(account);
        return accountResponseDto;
    }

    @Override
    public PagedDataDto<AccountResponseDto> fetchAll(Pageable pageable) {

        Page<Account> accountList = accountRepository.findAllByDeletedIsFalse(pageable);
        return AccountMapper.toPagedDto(accountList);

    }

    @Override
    public AccountResponseDto updateAccount(Long id, AccountUpdateDto accountUpdateDto) {

        Account accountToUpdate = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "id", id.toString()));

        if(accountToUpdate.getDeleted())
            throw new ResourceNotFoundException("Account", "id", id.toString());

        Account account = AccountMapper.accountUpdateToAccount(accountUpdateDto, accountToUpdate);

        Account updatedAccount = accountRepository.save(account);

        AccountResponseDto accountResponseDto = AccountMapper.toDto(updatedAccount);

        return accountResponseDto;
    }

    @Override
    public boolean deleteAccount(Long id) {
        Optional<Account> optionalAccount = accountRepository.findByIdAndDeletedIsFalse(id);

        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();

            account.setDeleted(true);
            accountRepository.save(account);
            return true;
        } else {
            throw new ResourceNotFoundException("Account", "id", id.toString());
        }

    }


}
