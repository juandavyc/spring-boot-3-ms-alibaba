package com.juandavyc.inventory.helper;

import com.juandavyc.inventory.constants.InventoryResponseCode;
import com.juandavyc.inventory.dto.response.ResponseDto;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ResponseBuilder {

    public static <T> ResponseDto<T> response(
            InventoryResponseCode inventoryResponseCode,
            T data
            ){
        return new ResponseDto<>(
                inventoryResponseCode.getStatus(),
                inventoryResponseCode.getStatus().value(),
                inventoryResponseCode.getMessage(),
                data
        );
    }
}
