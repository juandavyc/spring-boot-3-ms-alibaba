package com.juandavyc.accounts.repository;

import com.juandavyc.accounts.entity.Account;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;


import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Page<Account> findAllByDeletedIsFalse(Pageable pageable);

//    boolean existsByUsernameAndDeletedIsFalse(String username);
//    boolean existsByEmailAndDeletedIsFalse(String email);

    Optional<Account> findByIdAndDeletedIsFalse(Long id);
    Optional<Account> findByUsername(String userName);
    Optional<Account> findByEmail(String email);



}
