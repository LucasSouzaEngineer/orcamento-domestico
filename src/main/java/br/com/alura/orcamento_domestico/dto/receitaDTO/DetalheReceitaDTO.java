package br.com.alura.orcamento_domestico.dto.receitaDTO;

import br.com.alura.orcamento_domestico.model.Receita;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DetalheReceitaDTO(
        Long id,
        String descricao,
        BigDecimal valor,
        LocalDate data
) {

    public DetalheReceitaDTO(Receita receita){
        this(receita.getId(), receita.getDescricao(), receita.getValor(), receita.getData());
    }
}
