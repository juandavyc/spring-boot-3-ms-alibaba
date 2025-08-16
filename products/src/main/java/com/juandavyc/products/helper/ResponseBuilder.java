package com.juandavyc.products.helper;

import com.juandavyc.products.dto.response.ResponseDto;

public class ResponseBuilder {

   public static <T> ResponseDto<T> response(Integer status, String message, T data) {
      return new ResponseDto<>(
              status,
              message,
              data
      );
   }

}
