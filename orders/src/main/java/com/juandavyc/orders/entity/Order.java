package com.juandavyc.orders.entity;

import com.juandavyc.orders.entity.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class Order extends BaseEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID accountId;

    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> items;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(accountId, order.accountId) && status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountId, status);
    }

}
