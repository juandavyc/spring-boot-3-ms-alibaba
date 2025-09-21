package com.juandavyc.orders.dto.response;

import com.juandavyc.orders.dto.feign.response.AccountResponseDto;
import com.juandavyc.orders.entity.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
//@ToString
@SuperBuilder
public class OrderResponseDto {

   private UUID id;
   private UUID accountId;
   private BigDecimal total;
   private OrderStatus status;
   private AccountResponseDto account = null;

}
