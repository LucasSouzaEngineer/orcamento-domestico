package br.com.alura.orcamento_domestico.dto.metasOrcamentoDTO;

import br.com.alura.orcamento_domestico.model.CategoriaMetaOrcamento;
import jakarta.validation.constraints.NotNull;

public record CadastroMetasOrcamentoDTO(
        @NotNull
        CategoriaMetaOrcamento categoria,
        @NotNull
        Double porcentagem
) {
}
