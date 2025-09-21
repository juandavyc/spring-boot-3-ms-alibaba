package com.juandavyc.orders.controller;

import com.juandavyc.orders.constants.OrderResponseCode;
import com.juandavyc.orders.dto.request.OrderRequestDto;
import com.juandavyc.orders.dto.response.*;
import com.juandavyc.orders.helper.ResponseBuilder;
import com.juandavyc.orders.service.IOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api")
@Validated
@RequiredArgsConstructor
public class OrderController {

    private final IOrderService orderService;

    @PostMapping
    public ResponseEntity<ResponseDto<OrderResponseDto>> createOrder(
            @Valid @RequestBody OrderRequestDto orderRequestDto
    ) {
        OrderResponseDto responseDto = orderService.createOrder(orderRequestDto);
        OrderResponseCode responseCode = OrderResponseCode.CREATED;
        return ResponseEntity
                .status(responseCode.getStatus())
                .body(ResponseBuilder.response(responseCode, responseDto));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<PagedDataDto<OrderResponseDto>>> fetchOrders(
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        final var responseDto = orderService.fetchOrders(pageable);
        OrderResponseCode responseCode = OrderResponseCode.SUCCESS;
        return ResponseEntity
                .status(responseCode.getStatus())
                .body(ResponseBuilder.response(responseCode, responseDto));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseDto<OrderDetailResponseDto>> fetchOrderById(
            @PathVariable UUID id
    ) {

        final var responseDto = orderService.fetchOrderById(id);
        OrderResponseCode responseCode = OrderResponseCode.SUCCESS;
        return ResponseEntity
                .status(responseCode.getStatus())
                .body(ResponseBuilder.response(responseCode, responseDto));

    }
    @GetMapping(path = "/{id}/account")
    public ResponseEntity<ResponseDto<OrderResponseDto>> fetchOrderByIdWithAccount(
            @PathVariable UUID id
    ) {

        final var responseDto = orderService.fetchOrderByIdWithAccount(id);
        OrderResponseCode responseCode = OrderResponseCode.SUCCESS;
        return ResponseEntity
                .status(responseCode.getStatus())
                .body(ResponseBuilder.response(responseCode, responseDto));

    }

    @GetMapping(path = "/{id}/products")
    public ResponseEntity<ResponseDto<OrderDetailResponseDto>> fetchOrderByIdWithProducts(
            @PathVariable UUID id
    ) {

        final var responseDto = orderService.fetchOrderByIdWithProduct(id);
        OrderResponseCode responseCode = OrderResponseCode.SUCCESS;
        return ResponseEntity
                .status(responseCode.getStatus())
                .body(ResponseBuilder.response(responseCode, responseDto));

    }

}
