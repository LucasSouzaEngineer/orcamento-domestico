package br.com.alura.orcamento_domestico.dto;

import jakarta.validation.constraints.NotNull;

import java.time.YearMonth;

public record AnoMesDTO(
        @NotNull(message = "Obrigatório informar Ano e Mês")
        YearMonth anoMes
) {
}
