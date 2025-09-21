package com.juandavyc.products.helper;

import com.juandavyc.products.contants.ProductResponseCode;
import com.juandavyc.products.dto.response.ResponseDto;

public class ResponseBuilder {

   public static <T> ResponseDto<T> response(ProductResponseCode productResponseCode, T data) {
      return new ResponseDto<>(
              productResponseCode.getStatus().value(),
              productResponseCode.getMessage(),
              data
      );
   }

}
