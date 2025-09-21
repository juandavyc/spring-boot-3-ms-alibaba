package com.juandavyc.inventory.repository;

import com.juandavyc.inventory.entity.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;
import java.util.UUID;

public interface InventoryRepository extends JpaRepository<Inventory, UUID> {

    @Override
    Page<Inventory> findAll(Pageable pageable);

    boolean existsByProductId(UUID productId);

    Optional<Inventory> findByIdAndDeletedIsFalse(UUID id);
    Optional<Inventory> findByProductIdAndDeletedIsFalse(UUID productId);


}
