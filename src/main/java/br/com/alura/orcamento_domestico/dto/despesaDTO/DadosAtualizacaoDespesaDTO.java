package br.com.alura.orcamento_domestico.dto.despesaDTO;

import br.com.alura.orcamento_domestico.dto.DadosDataDTO;
import br.com.alura.orcamento_domestico.model.CategoriaDespesa;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosAtualizacaoDespesaDTO(
        Long id,
        String descricao,
        BigDecimal valor,
        LocalDate data,
        CategoriaDespesa categoria
){
}
