package com.juandavyc.orders.service.cache;

import com.juandavyc.orders.dto.feign.response.AccountResponseDto;
import com.juandavyc.orders.dto.feign.response.AccountsResponseDto;
import com.juandavyc.orders.dto.response.ResponseDto;
import com.juandavyc.orders.service.client.AccountsFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountsFeignClient accountsFeignClient;

//    public boolean existsAccountById(@PathVariable UUID id){
//        return accountsFeignClient.existsAccountById(id);
//    }


//    @Cacheable(value = "account", key = "#id")
    public ResponseDto<AccountResponseDto> fetchAccountById(@PathVariable UUID id){
        return accountsFeignClient.fetchAccountById(id);
    }
}
