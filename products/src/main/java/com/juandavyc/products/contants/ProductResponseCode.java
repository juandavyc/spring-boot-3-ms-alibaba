package com.juandavyc.products.contants;

import jakarta.persistence.Table;
import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter

public enum ProductResponseCode {
    CREATED(HttpStatus.CREATED, "Product created successfully"),
    SUCCESS(HttpStatus.OK, "Request processed successfully");

    private final HttpStatus status;
    private final String message;

    @Override
    public String toString() {
        return "ProductResponseCode{" +
                "httpStatus=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
