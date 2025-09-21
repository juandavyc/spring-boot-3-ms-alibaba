package com.juandavyc.products.dto.request;

import com.juandavyc.products.validation.groups.OnCreate;
import com.juandavyc.products.validation.groups.OnUpdate;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductRequestDto {

    @NotEmpty(message = "code cannot be empty", groups = OnCreate.class)
    @Size(min = 1, max = 4, message = "Code must be between 1 and 4 characters")
    private String code;

    @NotEmpty(message = "name cannot be empty", groups = OnCreate.class)
    @Size(min = 3, max = 200, message = "Name must be between 3 and 200 characters")
    private String name;

    @NotEmpty(message = "description cannot be empty", groups = OnCreate.class)
    @Size(min = 3, max = 500, message = "Name must be between 1 and 500 characters")
    private String description;

    @NotNull(message = "Price cannot be null", groups = OnCreate.class)
    @DecimalMin(value = "100.0", message = "price must be greater than 100.0")
    @DecimalMax(value="1000000.0", message = "price must be less than 10000000.0")
    private BigDecimal price;

}
