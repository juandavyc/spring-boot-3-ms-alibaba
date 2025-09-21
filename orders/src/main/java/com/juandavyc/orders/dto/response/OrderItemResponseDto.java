package com.juandavyc.orders.dto.response;

import com.juandavyc.orders.dto.feign.response.ProductResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor

public class OrderItemResponseDto {

    private UUID id;
    private UUID productId;
    private Integer quantity;
    private BigDecimal price;
    private ProductResponseDto product;

}
