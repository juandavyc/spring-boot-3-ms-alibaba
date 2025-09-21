package com.juandavyc.orders.dto.feign.response;

import com.juandavyc.orders.dto.response.ResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductsResponseDto  {
    List <ProductResponseDto> products;

}