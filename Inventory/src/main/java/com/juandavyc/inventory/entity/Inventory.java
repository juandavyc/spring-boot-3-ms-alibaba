package com.juandavyc.inventory.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "inventories")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Inventory extends BaseEntity {

    @Id
    @GeneratedValue
    private UUID id;
    private UUID productId;
    private Long quantity;
    private Boolean deleted = false;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return Objects.equals(id, inventory.id) && Objects.equals(productId, inventory.productId) && Objects.equals(quantity, inventory.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId, quantity, deleted);
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", deleted=" + deleted +
                '}';
    }
}
