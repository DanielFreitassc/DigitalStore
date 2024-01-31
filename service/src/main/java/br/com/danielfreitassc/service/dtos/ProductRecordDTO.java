package br.com.danielfreitassc.service.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRecordDTO(@NotBlank @NotNull String name, @NotNull BigDecimal value) {
    
}
