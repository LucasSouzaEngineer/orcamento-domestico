package br.com.alura.orcamento_domestico.dto.resumoMesDTO;

import java.math.BigDecimal;
import java.util.List;

public record ResumoMesDTO(
        BigDecimal totalReceitas,
        BigDecimal totalDespesas,
        BigDecimal saldo,
        List<TotalCategoriaDTO> totalCategoria
) {
    public ResumoMesDTO(BigDecimal receitas, BigDecimal despesas, List<TotalCategoriaDTO> totalCategoria){
        this(receitas, despesas, receitas.subtract(despesas), totalCategoria);
    }
}
