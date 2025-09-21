package com.juandavyc.orders.mapper;

import com.juandavyc.orders.dto.feign.response.AccountResponseDto;
import com.juandavyc.orders.dto.feign.response.ProductResponseDto;
import com.juandavyc.orders.dto.request.OrderItemRequestDto;
import com.juandavyc.orders.dto.request.OrderRequestDto;
import com.juandavyc.orders.dto.response.OrderDetailResponseDto;
import com.juandavyc.orders.dto.response.OrderItemResponseDto;
import com.juandavyc.orders.dto.response.OrderResponseDto;
import com.juandavyc.orders.dto.response.PagedDataDto;
import com.juandavyc.orders.entity.Order;
import com.juandavyc.orders.entity.OrderItem;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class OrderMapper {

    public static OrderDetailResponseDto toDetailWithProductsDto(
            Order order, List<ProductResponseDto> products
    ) {
        final var orderDetailsDto = toDetailDto(order);
        // creo el mapa, es mas eficiente
        Map<UUID, ProductResponseDto> productsMap = products.stream()
                .collect(Collectors.toMap(ProductResponseDto::getId, Function.identity()));

        orderDetailsDto.getItems()
                .forEach(item ->
                        item.setProduct(productsMap.getOrDefault(item.getProductId(), new ProductResponseDto()))
                );
        return orderDetailsDto;
    }

    public static Order toEntity(OrderRequestDto orderRequestDto, List<ProductResponseDto> products) {
        Order order = new Order();

        order.setAccountId(orderRequestDto.getAccountId());
        order.setStatus(orderRequestDto.getStatus());
        order.setItems(toOrderItemsListEntity(orderRequestDto.getItems(), products, order));
        order.setTotal(calculateTotal(order.getItems()));

        return order;
    }

    private static BigDecimal calculateTotal(List<OrderItem> items) {

        return items.stream()
                .map(OrderItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static OrderResponseDto toDto(Order order) {

        return OrderResponseDto.builder()
                .id(order.getId())
                .accountId(order.getAccountId())
                .total(order.getTotal())
                .status(order.getStatus())
                .account(null)
                .build();
    }

    public static OrderResponseDto toDtoWithAccount(Order order, AccountResponseDto account) {
        OrderResponseDto dto = toDto(order);
        dto.setAccount(account);
        return dto;
    }


    public static OrderDetailResponseDto toDetailDto(Order order) {

        return OrderDetailResponseDto.builder()
                .id(order.getId())
                .accountId(order.getId())
                .total(order.getTotal())
                .status(order.getStatus())
                .items(toOrderItemListResponse(order.getItems()))
                .build();

    }

    public static PagedDataDto<OrderResponseDto> toPagedDto(Page<Order> orders) {
        List<OrderResponseDto> orderList = orders.getContent()
                .stream()
                .map(OrderMapper::toDto)
                .peek(System.out::println)
                .toList();

        return new PagedDataDto<>(
                orderList,
                orders.getNumber(),
                orders.getSize(),
                orders.getTotalElements(),
                orders.getTotalPages(),
                orders.isFirst(),
                orders.isLast(),
                orders.isEmpty(),
                orders.hasPrevious(),
                orders.hasNext()
        );
    }

    private static List<OrderItemResponseDto> toOrderItemListResponse(List<OrderItem> orderItemList) {

        return orderItemList.stream()
                .map(orderItem -> new OrderItemResponseDto(
                        orderItem.getId(),
                        orderItem.getProductId(),
                        orderItem.getQuantity(),
                        orderItem.getPrice(),
                        null
                )).toList();
    }

    private static List<OrderItem> toOrderItemsListEntity(
            List<OrderItemRequestDto> orderItemRequestDto,
            List<ProductResponseDto> productsResponseDto,
            Order order) {

        Map<UUID, BigDecimal> productsMap = productsResponseDto.stream()
                .collect(Collectors
                        .toMap(ProductResponseDto::getId, ProductResponseDto::getPrice));

        return orderItemRequestDto.stream()
                .map(orderItem -> {
                    OrderItem item = new OrderItem();
                    item.setProductId(orderItem.getProductId());
                    item.setQuantity(orderItem.getQuantity());

                    BigDecimal price = productsMap.getOrDefault(item.getProductId(), new BigDecimal(0));

                    item.setPrice(price.multiply(BigDecimal.valueOf(item.getQuantity())));

                    item.setOrder(order);
                    return item;
                }).toList();

    }


}
