package br.com.alura.orcamento_domestico.dto.metasOrcamentoDTO;

import br.com.alura.orcamento_domestico.model.CategoriaMetaOrcamento;
import br.com.alura.orcamento_domestico.model.MetaOrcamento;

public record DetalheMetaOrcamentoDTO(
        Long id,
        CategoriaMetaOrcamento categoria,
        Double porcentagem
) {
    public DetalheMetaOrcamentoDTO(MetaOrcamento metaOrcamento){
        this(metaOrcamento.getId(), metaOrcamento.getCategoria(), metaOrcamento.getPorcentagem());
    }
}
