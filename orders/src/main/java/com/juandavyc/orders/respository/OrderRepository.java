package com.juandavyc.orders.respository;

import com.juandavyc.orders.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

//    @EntityGraph(attributePaths = {"items"})
    Page<Order> findAll(Pageable pageable);

    Optional<Order> findById(UUID id);

}
