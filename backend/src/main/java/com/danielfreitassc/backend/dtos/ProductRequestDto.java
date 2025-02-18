package com.danielfreitassc.backend.dtos;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequestDto(
    @NotBlank(message = "O campo nome não pode estar em branco")
    String name,
    MultipartFile image,
    @NotNull(message = "O campo preço não pode ser nulo")
    BigDecimal price
) {
    
}
