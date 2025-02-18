package com.danielfreitassc.backend.dtos;

import jakarta.validation.constraints.NotBlank;

public record ChatRequestUpdateDto(
    @NotBlank(message = "ID da mensagem precisa ser preenchido")
    String id,
    @NotBlank(message = "Mensagem não pode ser um campo vazio")
    String message,
    @NotBlank(message = "Nome do usuário não pode ser um campo vazio")
    String username
) {
    
}
