package com.juandavyc.orders.dto.response;

import com.juandavyc.orders.entity.enums.OrderStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@SuperBuilder
@ToString(callSuper = true)
public class OrderDetailResponseDto extends OrderResponseDto {

    private List<OrderItemResponseDto> items;


}
