package com.juandavyc.orders.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class OrderItemRequestDto {

    @NotNull(message = "ProductId is required")
    private UUID productId;
    @Min(value = 1, message = "Quantity must be greater than 0")
    private Integer quantity;


}
