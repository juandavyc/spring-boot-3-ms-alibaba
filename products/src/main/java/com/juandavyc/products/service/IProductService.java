package com.juandavyc.products.service;

import com.juandavyc.products.dto.request.ProductRequestDto;
import com.juandavyc.products.dto.response.PagedResponseDto;
import com.juandavyc.products.dto.response.ProductResponseDto;
import com.juandavyc.products.entity.Product;
import org.springframework.data.domain.Pageable;

public interface IProductService {

    Long createProduct(ProductRequestDto productRequestDto);

    PagedResponseDto fetchProducts(Pageable pageable);

    ProductResponseDto fetchProduct(Long id);
}
