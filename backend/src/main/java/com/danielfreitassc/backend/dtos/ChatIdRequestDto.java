package com.danielfreitassc.backend.dtos;

import jakarta.validation.constraints.NotBlank;

public record ChatIdRequestDto(
    @NotBlank(message = "ID necessario para excluir mensagem")
    String id
) {
    
}
