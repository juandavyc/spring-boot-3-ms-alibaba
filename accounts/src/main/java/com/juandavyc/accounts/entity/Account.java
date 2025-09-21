package com.juandavyc.accounts.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Account extends BaseEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String username;
    private String email;

    @Column(nullable = false)
    private Boolean deleted = false;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(username, account.username) && Objects.equals(email, account.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}

