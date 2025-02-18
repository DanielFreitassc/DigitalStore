package com.danielfreitassc.backend.dtos;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record ProductResponseDto(
    String id,
    String name,
    String imageId,
    BigDecimal price,
    Timestamp createdAt
) {
    
}
