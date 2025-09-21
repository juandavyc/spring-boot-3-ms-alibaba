package com.juandavyc.accounts.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record AccountResponseDto(
        UUID id,
        String username,
        String email,
        Boolean deleted,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
