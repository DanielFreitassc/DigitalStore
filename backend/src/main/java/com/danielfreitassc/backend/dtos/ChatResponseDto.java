package com.danielfreitassc.backend.dtos;

import java.time.LocalDateTime;

public record ChatResponseDto(
    String id,
    String message,
    String username,
    LocalDateTime createdAt
) {
    
}
