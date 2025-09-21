package com.juandavyc.products.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

    private UUID id;
    private String code;
    private String name;
    private String description;
    private BigDecimal price;

}
