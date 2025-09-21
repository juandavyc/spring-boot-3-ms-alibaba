package com.juandavyc.orders.service.client;

import com.juandavyc.orders.dto.feign.response.AccountResponseDto;
import com.juandavyc.orders.dto.feign.response.AccountsResponseDto;
import com.juandavyc.orders.dto.response.ResponseDto;
import com.juandavyc.orders.service.client.fallback.AccountsFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;


@FeignClient(
        name = "accounts",
        fallback = AccountsFallback.class
)
public interface AccountsFeignClient {

    @GetMapping("/api/{id}/exists")
    boolean existsAccountById(@PathVariable UUID id);

    @GetMapping("/api/{id}")
    ResponseDto<AccountResponseDto> fetchAccountById(@PathVariable UUID id);

}
