package com.juandavyc.products.service;

import com.juandavyc.products.dto.request.ProductRequestDto;
import com.juandavyc.products.dto.response.PagedResponseDto;
import com.juandavyc.products.dto.response.ProductResponseDto;
import com.juandavyc.products.dto.response.ProductsBatchResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IProductService {

    UUID createProduct(ProductRequestDto productRequestDto);

    PagedResponseDto fetchProducts(Pageable pageable);

    ProductResponseDto fetchProduct(UUID id);

    ProductResponseDto updateProduct(UUID id, ProductRequestDto productRequestDto);

    boolean deleteProduct(UUID id);

    List<ProductResponseDto> fetchProductsByIds(List<UUID> ids);
}
