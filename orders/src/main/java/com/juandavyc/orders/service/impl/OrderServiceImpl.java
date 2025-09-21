package com.juandavyc.orders.service.impl;

import com.juandavyc.orders.dto.feign.response.ProductResponseDto;
import com.juandavyc.orders.dto.request.OrderItemRequestDto;
import com.juandavyc.orders.dto.request.OrderRequestDto;
import com.juandavyc.orders.dto.response.OrderDetailResponseDto;
import com.juandavyc.orders.dto.response.OrderItemResponseDto;
import com.juandavyc.orders.dto.response.OrderResponseDto;
import com.juandavyc.orders.dto.response.PagedDataDto;
import com.juandavyc.orders.entity.Order;
import com.juandavyc.orders.entity.OrderItem;
import com.juandavyc.orders.exception.ResourceNotFoundException;
import com.juandavyc.orders.mapper.OrderMapper;
import com.juandavyc.orders.respository.OrderRepository;
import com.juandavyc.orders.service.IOrderService;

import com.juandavyc.orders.service.cache.AccountService;
import com.juandavyc.orders.service.cache.ProductService;
import com.juandavyc.orders.service.client.AccountsFeignClient;
import com.juandavyc.orders.service.client.ProductsFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository orderRepository;
//    private final AccountsFeignClient accountsFeignClient;
    private final AccountService accountService;
//    private final ProductsFeignClient productService;
    private final ProductService productService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {

        UUID accountId = orderRequestDto.getAccountId();

        // verify if the account exists
//        boolean existsAccount = accountService.existsAccountById(accountId);
//        if (!existsAccount) {
//            throw new ResourceNotFoundException("account", "id", accountId.toString());
//        }

        List<UUID> ids = orderRequestDto.getItems().stream()
                .map(OrderItemRequestDto::getProductId)
                .toList();

        final var products = productService.fetchProductsByIds(ids).getProducts();

//        List<ProductResponseDto>  products = productResponse.getData();

        if (products.isEmpty() || products.size() != ids.size()) {
            throw new ResourceNotFoundException("Product", "ids are different: ", ids.toString());
        }
        Order orderEntity = OrderMapper.toEntity(orderRequestDto, products);


        Order orderSaved = orderRepository.save(orderEntity);
        return OrderMapper.toDto(orderSaved);
    }

    @Override
    public PagedDataDto<OrderResponseDto> fetchOrders(Pageable pageable) {
        final var orders = orderRepository.findAll(pageable);
//        orders.forEach(Order::getItems);
        return OrderMapper.toPagedDto(orders);
    }

    @Override
    public OrderDetailResponseDto fetchOrderById(UUID id) {
        final var order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("order", "id", id.toString()));


        return OrderMapper.toDetailDto(order);

    }

    @Override
    public OrderResponseDto fetchOrderByIdWithAccount(UUID id) {
        final var orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("order", "id", id.toString()));

//        final var accountResponse = accountService.fetchAccountById(orderEntity.getAccountId());

//        final var order = OrderMapper.toDtoWithAccount(orderEntity, null);


        return null;
    }

    @Override
    public OrderDetailResponseDto fetchOrderByIdWithProduct(UUID id) {

        final var order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("order", "id", id.toString()));

        final var ids = order.getItems().stream()
                .map(OrderItem::getProductId)
                .toList();

        final var products = productService.fetchProductsByIds(ids).getProducts();

        return OrderMapper.toDetailWithProductsDto(order, products);
    }


}
