package com.juandavyc.products.repository;

import com.juandavyc.products.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    boolean existsByCodeAndDeletedIsFalse(String code);

    boolean existsByNameAndDeletedIsFalse(String name);

    Page<Product> findAllByDeletedIsFalse(Pageable pageable);

    Optional<Product> findByIdAndDeletedIsFalse(UUID id);

}

