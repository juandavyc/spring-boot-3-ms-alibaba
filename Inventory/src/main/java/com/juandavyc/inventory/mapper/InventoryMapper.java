package com.juandavyc.inventory.mapper;

import com.juandavyc.inventory.dto.request.InventoryRequestDto;
import com.juandavyc.inventory.dto.response.InventoryResponseDto;
import com.juandavyc.inventory.dto.response.PagedDataDto;
import com.juandavyc.inventory.entity.Inventory;
import org.springframework.data.domain.Page;

import java.util.List;

public class InventoryMapper {

    public static InventoryResponseDto inventoryToInventoryResponseDto(Inventory inventory) {
        return new InventoryResponseDto(
                inventory.getId(),
                inventory.getProductId(),
                inventory.getQuantity()
        );
    }

    public static Inventory inventoryDtoToInventory(InventoryRequestDto inventoryRequestDto) {
        Inventory inventory = new Inventory();
        inventory.setProductId(inventoryRequestDto.getProductId());
        inventory.setQuantity(inventoryRequestDto.getQuantity());
        inventory.setCreatedBy("ADMIN");
        return inventory;
    }

    public static Inventory inventoryUpdateDtoToInventory(InventoryRequestDto inventoryRequestDto, Inventory inventory) {
        if (inventoryRequestDto.getProductId() != null) {
            inventory.setProductId(inventoryRequestDto.getProductId());
        }
        if (inventoryRequestDto.getQuantity() != null) {
            inventory.setQuantity(inventoryRequestDto.getQuantity());
        }
        inventory.setUpdatedBy("ADMIN");
        return inventory;
    }


    public static PagedDataDto<InventoryResponseDto> toPagedDto(Page<Inventory> inventory) {
        List<InventoryResponseDto> inventoryList = inventory.getContent().stream()
                .map(InventoryMapper::inventoryToInventoryResponseDto)
                .toList();
        return new PagedDataDto<>(
                inventoryList,
                inventory.getNumber(),
                inventory.getSize(),
                inventory.getTotalElements(),
                inventory.getTotalPages(),
                inventory.isFirst(),
                inventory.isLast(),
                inventory.isEmpty(),
                inventory.hasPrevious(),
                inventory.hasNext()
        );
    }
}
