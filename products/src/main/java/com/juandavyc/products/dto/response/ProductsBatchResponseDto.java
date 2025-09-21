package com.juandavyc.products.dto.response;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@ToString
public class ProductsBatchResponseDto {

    List<ProductResponseDto> found;
    List<UUID> notFound;

}
