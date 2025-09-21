package com.juandavyc.inventory.service;

import com.juandavyc.inventory.dto.request.InventoryRequestDto;
import com.juandavyc.inventory.dto.response.InventoryResponseDto;
import com.juandavyc.inventory.dto.response.PagedDataDto;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IInventoryService {

    PagedDataDto<InventoryResponseDto> fetchAllInventory(Pageable pageable);

    InventoryResponseDto createInventory(InventoryRequestDto requestDto);

    InventoryResponseDto findById(UUID id);
    InventoryResponseDto findByProductId(UUID id);

    InventoryResponseDto updateInventory(UUID id,InventoryRequestDto requestDto);

    boolean deleteProduct(UUID id);
}
