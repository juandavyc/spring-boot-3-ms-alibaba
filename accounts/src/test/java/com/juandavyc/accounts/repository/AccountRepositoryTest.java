package com.juandavyc.accounts.repository;

import com.juandavyc.accounts.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.domain.*;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountRepositoryTest {
//
//    @Autowired
//    private AccountRepository underTest;
//
//    @Container
//    @ServiceConnection
//    static PostgreSQLContainer<?> postgresqlContainer
//            = new PostgreSQLContainer<>(DockerImageName.parse("postgres:16.2"));
//
//
//    @Test
//    void canEstablishedConnection() {
//        assertThat(postgresqlContainer.isCreated()).isTrue();
//        assertThat(postgresqlContainer.isRunning()).isTrue();
//    }
//
//
//    @Test
//    void shouldReturnAccountByUsername() {
//
//        String username = UUID.randomUUID() + "-username";
//        String email = UUID.randomUUID() + "@email.com";
//
//        Account account = new Account(
//                null,
//                username,
//                email,
//                false
//        );
//
//        Account savedAccount = underTest.save(account);
//
//        assertThat(savedAccount)
//                .isNotNull();
//
//        assertThat(savedAccount.getId())
//                .isNotNull()
//                .isPositive();
//
//        //
//        Optional<Account> accountByUsername = underTest.findByUsername(username);
//
//        assertThat(accountByUsername)
//                .isPresent();
//
//        assertThat(accountByUsername.get())
//                .usingRecursiveComparison()
//                .ignoringFields("id")
//                .isEqualTo(account);
//
//
//    }
//
//    @Test
//    void shouldReturnNotDeletedAccountsWithPagination() {
//        List<Account> accountList = List.of(
//                new Account(null, "user1", "user1@test.com", false),
//                new Account(null, "user2", "user2@test.com", false),
//                new Account(null, "user3", "user3@test.com", true)
//        );
//
//        underTest.saveAll(accountList);
//        Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Direction.ASC, "id"));
//
//        Page<Account> pagedAccounts = underTest.findAllByDeletedIsFalse(pageable);
//
//        assertThat(pagedAccounts)
//                .isNotNull();
//
//        assertThat(pagedAccounts.getContent())
//                .isNotNull();
//
//        Predicate<Account> isDeleted = account -> account.getDeleted() == true;
//
//        long countNotDeletedAccounts = accountList.stream()
//                .filter(isDeleted.negate())
//                .count();
//
//        List<String> expectedUsernames = accountList.stream()
//                .filter(isDeleted.negate())
//                .map(Account::getUsername)
//                .toList();
//
//        assertThat(pagedAccounts.getNumberOfElements())
//                .isEqualTo(countNotDeletedAccounts);
//
//            assertThat(pagedAccounts.getContent())
//                    .extracting(Account::getUsername)
//                    .containsExactlyInAnyOrderElementsOf(expectedUsernames);
//
//    }


}