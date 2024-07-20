package br.com.alura.orcamento_domestico.dto.receitaDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CadastroReceitaDTO(
        @NotBlank
        String descricao,
        @NotNull
        BigDecimal valor,
        @NotNull
        LocalDate data
){
}
