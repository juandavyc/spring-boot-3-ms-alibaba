package com.juandavyc.products.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Product extends BaseEntity {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(unique = true, nullable = false)
    private String code;
    @Column(unique = true, nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private BigDecimal price;
    private Boolean deleted = false;


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(code, product.code) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", deleted=" + deleted +
                '}';
    }
}
