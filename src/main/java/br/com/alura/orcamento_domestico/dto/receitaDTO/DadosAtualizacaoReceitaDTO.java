package br.com.alura.orcamento_domestico.dto.receitaDTO;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosAtualizacaoReceitaDTO(
        @NotNull(message = "Obrigatório informar Id da Receita para atualizar dados")
        Long id,
        String descricao,
        BigDecimal valor,
        LocalDate data
){
}
