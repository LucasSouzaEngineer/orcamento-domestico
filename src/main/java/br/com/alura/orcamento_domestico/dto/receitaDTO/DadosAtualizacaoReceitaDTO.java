package br.com.alura.orcamento_domestico.dto.receitaDTO;

import br.com.alura.orcamento_domestico.dto.DadosDataDTO;

import java.math.BigDecimal;

public record DadosAtualizacaoReceitaDTO(
        Long id,
        String descricao,
        BigDecimal valor,
        DadosDataDTO data
){
}
