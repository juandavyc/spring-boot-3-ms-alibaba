package com.juandavyc.orders.helper;



import com.juandavyc.orders.constants.OrderResponseCode;
import com.juandavyc.orders.dto.response.ResponseDto;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ResponseBuilder {

    public static <T> ResponseDto<T> response(
            OrderResponseCode responseCode,
            T data
            ){
        return new ResponseDto<>(
                responseCode.getStatus(),
                responseCode.getStatus().value(),
                responseCode.getMessage(),
                data
        );
    }
}
