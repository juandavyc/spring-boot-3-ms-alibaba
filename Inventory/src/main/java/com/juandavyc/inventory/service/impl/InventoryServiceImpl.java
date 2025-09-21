package com.juandavyc.inventory.service.impl;

import com.juandavyc.inventory.dto.request.InventoryRequestDto;
import com.juandavyc.inventory.dto.response.InventoryResponseDto;
import com.juandavyc.inventory.dto.response.PagedDataDto;
import com.juandavyc.inventory.entity.Inventory;
import com.juandavyc.inventory.exception.ResourceAlreadyExistsException;
import com.juandavyc.inventory.exception.ResourceNotFoundException;
import com.juandavyc.inventory.mapper.InventoryMapper;
import com.juandavyc.inventory.repository.InventoryRepository;
import com.juandavyc.inventory.service.IInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements IInventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public PagedDataDto<InventoryResponseDto> fetchAllInventory(
            Pageable pageable
    ) {
        Page<Inventory> inventoryList = inventoryRepository.findAll(pageable);

        return InventoryMapper.toPagedDto(inventoryList);
    }

    @Override
    public InventoryResponseDto createInventory(InventoryRequestDto requestDto) {
        boolean inventoryExist = inventoryRepository
                .existsByProductId(requestDto.getProductId());
        if (inventoryExist) {
            throw new ResourceAlreadyExistsException("Inventory", "ProductId", requestDto.getProductId().toString());
        }
        Inventory inventory = InventoryMapper.inventoryDtoToInventory(requestDto);
        Inventory inventorySaved = inventoryRepository.save(inventory);
        return InventoryMapper.inventoryToInventoryResponseDto(inventorySaved);
    }

    @Override
    public InventoryResponseDto findById(UUID id) {
        Inventory inventory = inventoryRepository.findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory", "Id", id.toString()));
        return InventoryMapper.inventoryToInventoryResponseDto(inventory);
    }

    @Override
    public InventoryResponseDto findByProductId(UUID id) {
        Inventory inventory = inventoryRepository.findByProductIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory", "ProductId", id.toString()));
        return InventoryMapper.inventoryToInventoryResponseDto(inventory);
    }

    @Override
    public InventoryResponseDto updateInventory(UUID id, InventoryRequestDto requestDto) {
        Inventory inventory = inventoryRepository.findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory", "Id", id.toString()));

        Inventory updatedInventory =
                inventoryRepository.save(InventoryMapper.inventoryUpdateDtoToInventory(requestDto, inventory));

        return InventoryMapper.inventoryToInventoryResponseDto(updatedInventory);
    }

    @Override
    public boolean deleteProduct(UUID id) {
        Inventory inventoryToDelete = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory", "id", id.toString()));
        if (inventoryToDelete.getDeleted()) {
            throw new ResourceNotFoundException("Inventory", "id", id.toString());
        }
        inventoryToDelete.setDeleted(true);
        inventoryRepository.save(inventoryToDelete);
        return true;
    }
}
