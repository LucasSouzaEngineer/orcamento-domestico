package br.com.alura.orcamento_domestico.dto.receitaDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CadastroReceitaDTO(
        @NotBlank(message = "Obrigatório informar descrição")
        String descricao,
        @NotNull(message = "Obrigatório informar valor")
        BigDecimal valor,
        @NotNull(message = "Obrigatório informar data")
        LocalDate data
){
}
