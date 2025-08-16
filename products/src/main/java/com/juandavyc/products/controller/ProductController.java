package com.juandavyc.products.controller;

import com.juandavyc.products.contants.ProductResponseCode;
import com.juandavyc.products.dto.request.ProductRequestDto;
import com.juandavyc.products.dto.response.PagedResponseDto;
import com.juandavyc.products.dto.response.ProductResponseDto;
import com.juandavyc.products.dto.response.ResponseDto;
import com.juandavyc.products.entity.Product;
import com.juandavyc.products.helper.ResponseBuilder;
import com.juandavyc.products.service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/products")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final IProductService productService;

    @GetMapping
    public ResponseEntity<ResponseDto<PagedResponseDto>> fetchProducts(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ){

        PagedResponseDto products = productService.fetchProducts(pageable);
        ProductResponseCode responseCode = ProductResponseCode.SUCCESS;
        return ResponseEntity
                .status(responseCode.getStatus())
                .body(ResponseBuilder.response(
                        responseCode.getStatus().value(),
                        responseCode.getMessage(),
                        products)
                );

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseDto<ProductResponseDto>> fetchProduct(
            @PathVariable Long id
    ) {
        ProductResponseDto product = productService.fetchProduct(id);
        ProductResponseCode responseCode = ProductResponseCode.SUCCESS;
        return ResponseEntity
                .status(responseCode.getStatus())
                .body(ResponseBuilder.response(responseCode.getStatus().value(), responseCode.getMessage(), product));
    }


    @PostMapping
    public ResponseEntity<ResponseDto<Long>> addProduct(
            @Valid @RequestBody ProductRequestDto productRequestDto
    ) {
        Long productId = productService.createProduct(productRequestDto);
        ProductResponseCode responseCode = ProductResponseCode.CREATED;
        return ResponseEntity
                .status(responseCode.getStatus())
                .header(HttpHeaders.LOCATION, String.format("/api/product/%d", productId))
                .body(ResponseBuilder.response(responseCode.getStatus().value(), responseCode.getMessage(), productId));
    }



}
