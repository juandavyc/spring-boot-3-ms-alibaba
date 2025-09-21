package com.juandavyc.orders.dto.request;

import com.juandavyc.orders.entity.enums.OrderStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class OrderRequestDto {

    private UUID accountId;

    @NotNull(message = "Status cannot be empty")
    private OrderStatus status;

    @NotEmpty(message = "Items cannot be empty")
    private List<OrderItemRequestDto> items;


}
