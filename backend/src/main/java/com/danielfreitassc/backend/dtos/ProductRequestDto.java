package com.danielfreitassc.backend.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequestDto(
    @NotBlank(message = "O campo nome não pode estar em branco")
    String name,
    MultipartFile image,
    @Min(value = 0, message = "Valores negativos não são permitidos")
    @NotNull(message = "O campo preço não pode ser nulo")
    BigDecimal price
) {
    
}
