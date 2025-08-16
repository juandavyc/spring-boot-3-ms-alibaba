package com.juandavyc.accounts.dto.response;

import java.time.LocalDateTime;

public record AccountResponseDto(
        Long id,
        String username,
        String email,
        Boolean deleted,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
