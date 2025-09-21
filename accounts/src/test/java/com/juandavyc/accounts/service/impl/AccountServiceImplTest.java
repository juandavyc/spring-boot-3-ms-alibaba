package com.juandavyc.accounts.service.impl;

import com.juandavyc.accounts.dto.request.AccountRequestDto;
import com.juandavyc.accounts.dto.request.AccountUpdateDto;
import com.juandavyc.accounts.dto.response.AccountResponseDto;
import com.juandavyc.accounts.dto.response.PagedDataDto;
import com.juandavyc.accounts.entity.Account;
import com.juandavyc.accounts.exception.ResourceAlreadyExistsException;
import com.juandavyc.accounts.exception.ResourceNotFoundException;

import com.juandavyc.accounts.repository.AccountRepository;
import com.juandavyc.accounts.service.IAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
//
//
//    IAccountService underTest;
//
//    @Mock
//    AccountRepository accountRepository;
//
//    @Captor
//    ArgumentCaptor<Account> accountArgumentCaptor;
//
//    @BeforeEach
//    void setUp() {
//        underTest = new AccountServiceImpl(accountRepository);
//    }
//
//    @Test
//    void shouldReturnAccountById() {
//        long id = 1L;
//        String username = UUID.randomUUID() + "_name";
//        String email = username + "@test.com";
//        boolean isDeleted = false;
//
//        Account account = new Account(
//                id,
//                username,
//                email,
//                isDeleted
//        );
//
//        when(accountRepository.findByIdAndDeletedIsFalse(id))
//                .thenReturn(Optional.of(account));
//
//        AccountResponseDto accountById = underTest
//                .fetchAccountById(id);
//
//        verify(accountRepository)
//                .findByIdAndDeletedIsFalse(id);
//
//        assertThat(accountById.id()).isEqualTo(id);
//        assertThat(accountById.username()).isEqualTo(username);
//        assertThat(accountById.email()).isEqualTo(email);
//        assertThat(accountById.deleted()).isEqualTo(isDeleted);
//
//    }
//
//    @Test
//    void shouldThrowNotFoundWhenGivenInvalidIDWhileFetchingAccountById() {
//        long id = 9999L;
//
//        when(accountRepository.findByIdAndDeletedIsFalse(id))
//                .thenReturn(Optional.empty());
//
//        assertThatThrownBy(() -> underTest.fetchAccountById(id))
//                .isInstanceOf(ResourceNotFoundException.class)
//                .hasMessageContaining("Account", id);
//
//    }
//
//    @Test
//    void shouldThrowExistsWhenGivenUsernameAlreadyExistsWhileCreatingAccount() {
//        long id = 1L;
//        String username = UUID.randomUUID() + "_name";
//        String email = username + "@test.com";
//        boolean isDeleted = false;
//
//        Account account = new Account(
//                id,
//                username,
//                email,
//                isDeleted
//        );
//
//        AccountRequestDto accountRequestDto = new AccountRequestDto(
//                username,
//                null
//        );
//
//        when(accountRepository.findByUsername(username))
//                .thenReturn(Optional.of(account));
//
//        assertThatThrownBy(() -> underTest.createAccount(accountRequestDto))
//                .isInstanceOf(ResourceAlreadyExistsException.class)
//                .hasMessageContaining("Account", username);
//
//        verify(accountRepository, never()).save(account);
//
//    }
//
//    @Test
//    void shouldThrowExistsWhenGivenEmailAlreadyExistsWhileCreatingAccount() {
//        long id = 1L;
//        String username = UUID.randomUUID() + "_name";
//        String email = username + "@test.com";
//        boolean isDeleted = false;
//
//        Account account = new Account(
//                id,
//                username,
//                email,
//                isDeleted
//        );
//
//        AccountRequestDto accountRequestDto = new AccountRequestDto(
//                username,
//                email
//        );
//
//        when(accountRepository.findByEmail(email))
//                .thenReturn(Optional.of(account));
//
//        assertThatThrownBy(() -> underTest.createAccount(accountRequestDto))
//                .isInstanceOf(ResourceAlreadyExistsException.class)
//                .hasMessageContaining("Account", email);
//
//        verify(accountRepository, never()).save(account);
//
//    }
//
//    @Test
//    void shouldCreateAccount() {
//
//        long id = 1L;
//        String username = UUID.randomUUID() + "_name";
//        String email = username + "@test.com";
//        boolean isDeleted = false;
//
//        Account savedAccount = new Account(
//                id,
//                username,
//                email,
//                isDeleted
//        );
//
//        AccountRequestDto accountRequestDto = new AccountRequestDto(
//                username,
//                email
//        );
//
//        when(accountRepository.save(any(Account.class)))
//                .thenReturn(savedAccount);
//
//        Long generatedId = underTest.createAccount(accountRequestDto);
//
////
//        verify(accountRepository).save(accountArgumentCaptor.capture());
//        Account captured = accountArgumentCaptor.getValue();
//
//        assertThat(generatedId).isEqualTo(id);
//
//        assertThat(captured.getEmail()).isEqualTo(email);
//        assertThat(captured.getUsername()).isEqualTo(username);
//        assertThat(captured.getDeleted()).isEqualTo(isDeleted);
//
//    }
//
//    @Test
//    void shouldReturnPagedAccounts() {
//        List<Account> accountList = List.of(
//                new Account(1L, UUID.randomUUID().toString(), UUID.randomUUID() + "@test.com", false),
//                new Account(2L, UUID.randomUUID().toString(), UUID.randomUUID() + "@test.com", false),
//                new Account(3L, UUID.randomUUID().toString(), UUID.randomUUID() + "@test.com", false)
//        );
//
//        Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Direction.ASC, "id"));
//        Page<Account> page = new PageImpl<>(accountList, pageable, accountRepository.count());
//
//        when(accountRepository.findAllByDeletedIsFalse(pageable))
//                .thenReturn(page);
//
//        PagedDataDto<AccountResponseDto> pagedAccounts = underTest.fetchAll(pageable);
//
//        assertThat(pagedAccounts.getContent())
//                .isNotNull()
//                .hasSizeLessThanOrEqualTo(accountList.size());
//
//        assertThat(pagedAccounts.getContent())
//                .usingRecursiveComparison()
//                .isEqualTo(accountList);
//
//        assertThat(pagedAccounts.getIsFirstPage()).isTrue();
//        assertThat(pagedAccounts.getIsLastPage()).isTrue();
//        assertThat(pagedAccounts.getTotalPages()).isEqualTo(1);
//        assertThat(pagedAccounts.getSize()).isEqualTo(accountList.size());
//
//        verify(accountRepository).findAllByDeletedIsFalse(pageable);
//    }
//
//    @Test
//    void shouldThrowNotFoundWhenGivenInvalidIdWhileUpdatingAccount() {
//        long id = 9999L;
//        when(accountRepository.findById(id))
//                .thenReturn(Optional.empty());
//
//        assertThatThrownBy(() -> underTest.updateAccount(id, new AccountUpdateDto()))
//                .isInstanceOf(ResourceNotFoundException.class)
//                .hasMessageContaining("Account", id);
//
//        verify(accountRepository, never()).save(any(Account.class));
//    }
//
//    @Test
//    void shouldThrowNotFoundWhenGivenDeletedAccountWhileUpdatingAccount() {
//        long id = 1L;
//
//        Account account = new Account(id, null, null, true);
//
//        when(accountRepository.findById(id))
//                .thenReturn(Optional.of(account));
//
//        assertThatThrownBy(() -> underTest.updateAccount(id, new AccountUpdateDto()))
//                .isInstanceOf(ResourceNotFoundException.class)
//                .hasMessageContaining("Account", id);
//
//        verify(accountRepository, never())
//                .save(any(Account.class));
//
//    }
//
//    @Test
//    void shouldUpdateAccount() {
//        long id = 1L;
//        String newUsername = UUID.randomUUID() + "_user";
//
//        AccountUpdateDto accountRequestDto = new AccountUpdateDto(
//                newUsername,
//                newUsername + "@test.com"
//        );
//
//        Account account = new Account(
//                id,
//                "juan",
//                "juan@test.com",
//                false
//        );
//
//        Account saved = new Account(
//                id,
//                accountRequestDto.getUsername(),
//                accountRequestDto.getEmail(),
//                false
//        );
//
//        when(accountRepository.findById(id))
//                .thenReturn(Optional.of(account));
//
//        when(accountRepository.save(account))
//                .thenReturn(saved);
//
//        AccountResponseDto updatedAccount = underTest.updateAccount(id, accountRequestDto);
//
//        verify(accountRepository).save(accountArgumentCaptor.capture());
//
//        Account captured = accountArgumentCaptor.getValue();
//
//        assertThat(captured)
//                .usingRecursiveAssertion()
//                .ignoringFields("createdAt", "updatedAt")
//                .isEqualTo(saved);
//
//
//    }
//
//    @Test
//    void shouldDeleteAccount() {
//        long id = 1L;
//        Account account = new Account(
//                id,
//                null,
//                null,
//                false
//        );
//
//        Account deleted = new Account(
//                id,
//                null,
//                null,
//                true
//        );
//        when(accountRepository.findByIdAndDeletedIsFalse(id))
//                .thenReturn(Optional.of(deleted));
//
//        when(accountRepository.save(account))
//                .thenReturn(deleted);
//
//        boolean wasDeleted = underTest.deleteAccount(id);
//
//        assertThat(wasDeleted).isTrue();
//
//        verify(accountRepository).save(accountArgumentCaptor.capture());
//
//        Account captured = accountArgumentCaptor.getValue();
//
//        assertThat(captured)
//                .usingRecursiveComparison()
//                .ignoringFields("createdAt", "updatedAt")
//                .isEqualTo(deleted);
//    }
//
//    @Test
//    void shouldThrowNotFoundWhenGivenInvalidIdWhileDeletingAccount() {
//        long id = 7777L;
//
//        when(accountRepository.findByIdAndDeletedIsFalse(id))
//                .thenReturn(Optional.empty());
//
//        assertThatThrownBy(() -> underTest.deleteAccount(id))
//                .isInstanceOf(ResourceNotFoundException.class)
//                .hasMessageContaining("Account", id);
//
//        verify(accountRepository, never())
//                .save(any(Account.class));
//
//    }

}