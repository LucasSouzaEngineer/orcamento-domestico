package br.com.alura.orcamento_domestico.dto.resumoMesDTO;

import br.com.alura.orcamento_domestico.dto.metasOrcamentoDTO.DetalheMetaOrcamentoDTO;

import java.math.BigDecimal;
import java.util.List;

public record ResumoMesDTO(
        BigDecimal totalReceitas,
        BigDecimal totalDespesas,
        BigDecimal saldo,
        List<TotalCategoriaDTO> totalCategoria,
        List<DetalheMetaOrcamentoDTO> metasOrcamento
) {
    public ResumoMesDTO(BigDecimal receitas, BigDecimal despesas, List<TotalCategoriaDTO> totalCategoria, List<DetalheMetaOrcamentoDTO> metasOrcamento){
        this(receitas, despesas, receitas.subtract(despesas), totalCategoria, metasOrcamento);
    }
}
