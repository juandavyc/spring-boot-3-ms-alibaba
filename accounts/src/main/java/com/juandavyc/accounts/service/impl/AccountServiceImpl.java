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
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final AccountRepository accountRepository;

    @Override
    public UUID createAccount(AccountRequestDto accountRequestDto) {

        final var username = accountRequestDto.getUsername();
        final var email = accountRequestDto.getEmail();

        Optional<Account> accountByUsername = accountRepository.findByUsername(username);
        if (accountByUsername.isPresent())
            throw new ResourceAlreadyExistsException("Account", "username", username);
        Optional<Account> accountByEmail = accountRepository.findByEmail(email);
        if (accountByEmail.isPresent())
            throw new ResourceAlreadyExistsException("Account", "email", email);

        Account account = new Account();
        AccountMapper.toAccount(accountRequestDto, account);

        Account savedAccount = accountRepository.save(account);
        return savedAccount.getId();
    }


    @Override
    public AccountResponseDto fetchAccountById(UUID id) {

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
    public AccountResponseDto updateAccount(UUID id, AccountUpdateDto accountUpdateDto) {

        Account accountToUpdate = accountRepository.findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "id", id.toString()));

        if (accountToUpdate.getDeleted())
            throw new ResourceNotFoundException("Account", "id", id.toString());

        Account account = AccountMapper.accountUpdateToAccount(accountUpdateDto, accountToUpdate);

        Account updatedAccount = accountRepository.save(account);

        AccountResponseDto accountResponseDto = AccountMapper.toDto(updatedAccount);

        return accountResponseDto;
    }

    @Override
    public boolean deleteAccount(UUID id) {
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

    @Override
    public boolean existByEmail(String email) {
        return accountRepository.existsByEmailIgnoreCase(email);
    }

    @Override
    public boolean existByUsername(String username) {
        return accountRepository.existsByUsernameIgnoreCase(username);
    }

    @Override
    public boolean existByEmailInUse(String email, UUID id) {
        return accountRepository.existsByEmailIgnoreCaseAndIdNot(email, id);
    }

    @Override
    public boolean existByUsernameInUse(String username, UUID id) {
        return accountRepository.existsByUsernameIgnoreCaseAndIdNot(username, id);
    }

    @Override
    public boolean existById(UUID id) {
        return accountRepository.existsById(id);
    }

}
