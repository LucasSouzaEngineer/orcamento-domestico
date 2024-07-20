package br.com.alura.orcamento_domestico.dto.receitaDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosAtualizacaoReceitaDTO(
        Long id,
        String descricao,
        BigDecimal valor,
        LocalDate data
){
}
