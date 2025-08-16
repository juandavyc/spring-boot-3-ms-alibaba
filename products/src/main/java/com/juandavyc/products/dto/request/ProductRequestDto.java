package com.juandavyc.products.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductRequestDto {

    @NotNull(message = "code cannot be null")
    @NotEmpty(message = "code cannot be empty")
    private String code;

    @NotNull(message = "name cannot be null")
    @NotEmpty(message = "name cannot be empty")
    @Size(min = 3, max = 200, message = "Name must be between 3 and 200 characters")
    private String name;

    @NotNull(message = "description cannot be null")
    @NotEmpty(message = "description cannot be empty")
    private String description;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "100.0", message = "price must be greater than 100.0")
    @DecimalMax(value="1000000.0", message = "price must be less than 10000000.0")
    private Double price;

}
