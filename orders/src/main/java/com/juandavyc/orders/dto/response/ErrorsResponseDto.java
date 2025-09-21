package com.juandavyc.orders.dto.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class ErrorsResponseDto extends ErrorResponseDto {
    private Map<String, String> errors;
}
