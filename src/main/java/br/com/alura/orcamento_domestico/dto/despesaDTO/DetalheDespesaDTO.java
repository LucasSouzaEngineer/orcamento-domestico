package br.com.alura.orcamento_domestico.dto.despesaDTO;

import br.com.alura.orcamento_domestico.model.CategoriaDespesa;
import br.com.alura.orcamento_domestico.model.Despesa;
import br.com.alura.orcamento_domestico.model.Receita;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DetalheDespesaDTO(
        Long id,
        String descricao,
        BigDecimal valor,
        LocalDate data,
        CategoriaDespesa categoria
) {

    public DetalheDespesaDTO(Despesa despesa){
        this(despesa.getId(), despesa.getDescricao(), despesa.getValor(), despesa.getData(), despesa.getCategoria());
    }
}
