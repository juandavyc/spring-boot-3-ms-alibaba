package com.juandavyc.orders.service.client.fallback;

import com.juandavyc.orders.dto.feign.response.AccountResponseDto;
import com.juandavyc.orders.dto.feign.response.AccountsResponseDto;
import com.juandavyc.orders.dto.response.ResponseDto;
import com.juandavyc.orders.service.client.AccountsFeignClient;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class AccountsFallback implements AccountsFeignClient {

    @Override
    public boolean existsAccountById(UUID id) {
        return false;
    }

    @Override
    public AccountsResponseDto fetchAccountById(UUID id) {
        return null;
    }

//    @Override
//    public ResponseDto<AccountResponseDto> fetchAccountById(UUID id) {
//        return null;
//    }

}
