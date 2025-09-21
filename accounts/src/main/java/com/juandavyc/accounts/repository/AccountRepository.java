package com.juandavyc.accounts.repository;

import com.juandavyc.accounts.entity.Account;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {


    Page<Account> findAllByDeletedIsFalse(Pageable pageable);

    boolean existsByUsernameIgnoreCase(String username);
    boolean existsByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCaseAndIdNot(String email, UUID id);
    boolean existsByUsernameIgnoreCaseAndIdNot(String username, UUID id);

    Optional<Account> findByIdAndDeletedIsFalse(UUID id);
    Optional<Account> findByUsername(String userName);
    Optional<Account> findByEmail(String email);

}
