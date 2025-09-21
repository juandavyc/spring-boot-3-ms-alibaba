package com.juandavyc.inventory.dto.response;

import java.util.UUID;

public record InventoryResponseDto(
        UUID id,
        UUID productId,
        Long quantity
) {
}
