package com.danielfreitassc.backend.dtos;

import jakarta.validation.constraints.NotBlank;

public record ChatIdRequestDto(
    @NotBlank(message = "ID necessário para excluir mensagem")
    String id
) {
    
}
