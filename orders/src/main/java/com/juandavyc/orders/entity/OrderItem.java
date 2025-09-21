package com.juandavyc.orders.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "order_items")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrderItem {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID productId;

    private Integer quantity;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}

