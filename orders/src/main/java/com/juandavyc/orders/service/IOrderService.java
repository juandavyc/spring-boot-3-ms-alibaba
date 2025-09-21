package com.juandavyc.orders.service;

import com.juandavyc.orders.dto.request.OrderRequestDto;
import com.juandavyc.orders.dto.response.OrderDetailResponseDto;
import com.juandavyc.orders.dto.response.OrderItemResponseDto;
import com.juandavyc.orders.dto.response.OrderResponseDto;
import com.juandavyc.orders.dto.response.PagedDataDto;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IOrderService {

    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);

    PagedDataDto<OrderResponseDto> fetchOrders(Pageable pageable);

    OrderDetailResponseDto fetchOrderById(UUID id);

    OrderResponseDto fetchOrderByIdWithAccount(UUID id);

    OrderDetailResponseDto fetchOrderByIdWithProduct(UUID id);
}
