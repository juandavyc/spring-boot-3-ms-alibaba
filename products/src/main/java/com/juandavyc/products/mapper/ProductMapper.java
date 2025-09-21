package com.juandavyc.products.mapper;

import com.juandavyc.products.dto.request.ProductRequestDto;
import com.juandavyc.products.dto.response.PagedResponseDto;
import com.juandavyc.products.dto.response.ProductResponseDto;
import com.juandavyc.products.dto.response.ProductsBatchResponseDto;
import com.juandavyc.products.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProductMapper {


    public static ProductsBatchResponseDto toProductsBatchResponseDto(List<Product> products, List<UUID> ids) {

        List<ProductResponseDto> found = toListProductResponseDto(products);

        Set<UUID> foundIds = found.stream()
                .map(ProductResponseDto::getId)
                .collect(Collectors.toSet());

        List<UUID> notFound = ids.stream()
                .filter(id -> !foundIds.contains(id))
                .toList();

        return ProductsBatchResponseDto.builder()
                .found(found)
                .notFound(notFound)
                .build();

    }

    public static List<ProductResponseDto> toListProductResponseDto(List<Product> products) {
        return products.stream()
                .map(ProductMapper::productToProductResponseDto)
                .toList();
    }

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

    public static PagedResponseDto productListToPagedResponseDto(Page<Product> pagedProducts) {

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

    public static Product productUpdateDtoToProduct(ProductRequestDto productRequestDto, Product product) {

        if (productRequestDto.getCode() != null) {
            product.setCode(productRequestDto.getCode());
        }
        if (productRequestDto.getName() != null) {
            product.setName(productRequestDto.getName());
        }
        if (productRequestDto.getDescription() != null) {
            product.setDescription(productRequestDto.getDescription());
        }
        if (productRequestDto.getPrice() != null) {
            product.setPrice(productRequestDto.getPrice());
        }

        return product;
    }
}
