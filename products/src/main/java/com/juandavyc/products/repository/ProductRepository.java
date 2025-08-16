package com.juandavyc.products.repository;

import com.juandavyc.products.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByCodeAndDeletedIsFalse(String code);

    boolean existsByNameAndDeletedIsFalse(String name);

    Page<Product> findAllByDeletedIsFalse(Pageable pageable);


}

