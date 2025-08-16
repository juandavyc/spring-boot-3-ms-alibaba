package com.juandavyc.products.mapper;

import com.juandavyc.products.dto.request.ProductRequestDto;
import com.juandavyc.products.dto.response.PagedResponseDto;
import com.juandavyc.products.dto.response.ProductResponseDto;
import com.juandavyc.products.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public class ProductMapper {

    public static Product productRequestDtoToProduct(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setCode(productRequestDto.getCode());
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());

        return product;
    }

    public static ProductResponseDto productToProductResponseDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setId(product.getId());
        productResponseDto.setCode(product.getCode());
        productResponseDto.setName(product.getName());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setPrice(product.getPrice());

        return productResponseDto;
    }

    public static PagedResponseDto productListToPagedResponseDto(Page<Product> pagedProducts){

        List<ProductResponseDto> productList = pagedProducts.getContent().stream()
                .map(ProductMapper::productToProductResponseDto)
                .toList();

        return new PagedResponseDto(
                productList,
                pagedProducts.getTotalPages(),
                pagedProducts.getNumber(),
                pagedProducts.getSize(),
                pagedProducts.getNumberOfElements(),
                pagedProducts.isFirst(),
                pagedProducts.isLast(),
                pagedProducts.hasNext(),
                pagedProducts.hasPrevious(),
                pagedProducts.isEmpty()
        );
    }

}
