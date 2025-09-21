package com.juandavyc.orders.dto.feign.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Builder
@ToString
public class AccountResponseDto {

    private UUID id;
    private String username;
    private String email;

}
