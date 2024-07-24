package br.com.alura.orcamento_domestico.dto.despesaDTO;

import br.com.alura.orcamento_domestico.model.CategoriaDespesa;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosAtualizacaoDespesaDTO(
        @NotNull(message = "Obrigatório informar Id da Despesa para atualizar dados")
        Long id,
        String descricao,
        BigDecimal valor,
        LocalDate data,
        CategoriaDespesa categoria
){
}
