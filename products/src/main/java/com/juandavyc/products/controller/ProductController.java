package com.juandavyc.products.controller;

import com.juandavyc.products.contants.ProductResponseCode;
import com.juandavyc.products.dto.request.ProductRequestDto;
import com.juandavyc.products.dto.response.PagedResponseDto;
import com.juandavyc.products.dto.response.ProductResponseDto;
import com.juandavyc.products.dto.response.ProductsBatchResponseDto;
import com.juandavyc.products.dto.response.ResponseDto;
import com.juandavyc.products.helper.ResponseBuilder;
import com.juandavyc.products.service.IProductService;
import com.juandavyc.products.validation.groups.OnCreate;
import com.juandavyc.products.validation.groups.OnUpdate;

import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final IProductService productService;

    @GetMapping
    public ResponseEntity<ResponseDto<PagedResponseDto>> fetchProducts(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {

        PagedResponseDto products = productService.fetchProducts(pageable);
        ProductResponseCode responseCode = ProductResponseCode.SUCCESS;
        return ResponseEntity
                .status(responseCode.getStatus())
                .body(ResponseBuilder.response(
                        responseCode,
                        products)
                );

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseDto<ProductResponseDto>> fetchProduct(
            @PathVariable UUID id
    ) {
        ProductResponseDto product = productService.fetchProduct(id);
        ProductResponseCode responseCode = ProductResponseCode.SUCCESS;
        return ResponseEntity
                .status(responseCode.getStatus())
                .body(ResponseBuilder.response(responseCode, product));
    }

    @GetMapping(path = "/batch")
    public ResponseEntity<ResponseDto<List<ProductResponseDto>>> fetchProduct(
        @RequestParam List<UUID> ids
    ) {
        final var product = productService.fetchProductsByIds(ids);
        ProductResponseCode responseCode = ProductResponseCode.SUCCESS;
        return ResponseEntity
                .status(responseCode.getStatus())
                .body(ResponseBuilder.response(responseCode, product));
    }

    @PostMapping
    public ResponseEntity<ResponseDto<UUID>> addProduct(
            @Validated({OnCreate.class, Default.class}) @RequestBody ProductRequestDto productRequestDto
    ) {
        UUID productId = productService.createProduct(productRequestDto);
        ProductResponseCode responseCode = ProductResponseCode.CREATED;
        return ResponseEntity
                .status(responseCode.getStatus())
                .header(HttpHeaders.LOCATION, String.format("/api/product/%s", productId))
                .body(ResponseBuilder.response(responseCode, productId));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ResponseDto<ProductResponseDto>> updateProduct(
            @PathVariable UUID id,
            @Validated(OnUpdate.class) @RequestBody ProductRequestDto productRequestDto
    ) {

        ProductResponseDto productUpdated = productService.updateProduct(id, productRequestDto);
        ProductResponseCode responseCode = ProductResponseCode.SUCCESS;

        return ResponseEntity.status(responseCode.getStatus())
                .body(ResponseBuilder.response(responseCode, productUpdated));

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseDto<Map<String, Boolean>>> deleteProduct(
            @PathVariable UUID id
    ) {
        boolean isDeleted = productService.deleteProduct(id);
        ProductResponseCode responseCode =
                (isDeleted) ? ProductResponseCode.SUCCESS : ProductResponseCode.DELETE_FAILED;

        return ResponseEntity
                .status(responseCode.getStatus())
                .body(ResponseBuilder.response(
                                responseCode,
                                Map.of("isDeleted", isDeleted)
                        )
                );


    }


}
