package com.juandavyc.orders.service.client;


import com.juandavyc.orders.dto.feign.response.ProductResponseDto;
import com.juandavyc.orders.dto.feign.response.ProductsResponseDto;
import com.juandavyc.orders.dto.response.ResponseDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "products")
public interface ProductsFeignClient {



    @GetMapping(path = "/api/batch")
    ResponseDto<List<ProductResponseDto>> fetchProductsByIds(@RequestParam("ids") List<UUID> ids);

}
