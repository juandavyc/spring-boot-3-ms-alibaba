package com.juandavyc.products.service.impl;

import com.juandavyc.products.dto.request.ProductRequestDto;
import com.juandavyc.products.dto.response.PagedResponseDto;
import com.juandavyc.products.dto.response.ProductResponseDto;
import com.juandavyc.products.entity.Product;
import com.juandavyc.products.exception.ResourceAlreadyExistsException;
import com.juandavyc.products.exception.ResourceNotFoundException;
import com.juandavyc.products.mapper.ProductMapper;
import com.juandavyc.products.repository.ProductRepository;
import com.juandavyc.products.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;


    @Override
    public PagedResponseDto fetchProducts(Pageable pageable) {
        Page<Product> pagedProducts = productRepository.findAllByDeletedIsFalse(pageable);
        return ProductMapper.productListToPagedResponseDto(pagedProducts);
    }


    @Override
    public Long createProduct(ProductRequestDto productRequestDto) {

        String code = productRequestDto.getCode();
        if (productRepository.existsByCodeAndDeletedIsFalse(code)) {
            throw new ResourceAlreadyExistsException("Product", "code", code);
        }

        String name = productRequestDto.getName();
        if (productRepository.existsByNameAndDeletedIsFalse(name)) {
            throw new ResourceAlreadyExistsException("Product", "name", name);
        }

        Product product = ProductMapper.productRequestDtoToProduct(productRequestDto);
        // TODO auditory
        product.setCreatedBy("SYSTEM");
        product.setCreatedAt(LocalDateTime.now());

        return productRepository.save(product).getId();
    }


    @Override
    public ProductResponseDto fetchProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id.toString()));

        return ProductMapper.productToProductResponseDto(product);

    }
}
