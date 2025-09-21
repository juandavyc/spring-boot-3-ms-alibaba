package com.juandavyc.inventory.dto.request;

import com.juandavyc.inventory.validation.group.OnCreate;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class InventoryRequestDto {


    @NotNull(message = "ProductId cannot be null", groups = OnCreate.class)
    private UUID productId;

    @NotNull(message = "Quantity cannot be empty or null", groups = OnCreate.class)
    @Min(value = 1, message = "Quantity must be greater than 1")
    @Max(value = 1000, message = "Quantity must be less than 1000")
    private Long quantity;

}
