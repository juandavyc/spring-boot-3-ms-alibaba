package com.juandavyc.orders.service.cache;

import com.juandavyc.orders.dto.feign.response.ProductResponseDto;
import com.juandavyc.orders.dto.feign.response.ProductsResponseDto;
import com.juandavyc.orders.dto.response.ResponseDto;
import com.juandavyc.orders.service.client.ProductsFeignClient;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductsFeignClient productsFeignClient;

    @Cacheable(value = "products", key = "#ids")
    public ProductsResponseDto fetchProductsByIds(List<UUID> ids) {
        List<ProductResponseDto> data = productsFeignClient.fetchProductsByIds(ids).getData();
        return new ProductsResponseDto(data);
    }
}
