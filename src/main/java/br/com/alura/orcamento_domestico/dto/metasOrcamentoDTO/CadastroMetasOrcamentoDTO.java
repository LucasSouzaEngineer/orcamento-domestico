package br.com.alura.orcamento_domestico.dto.metasOrcamentoDTO;

import br.com.alura.orcamento_domestico.model.CategoriaMetaOrcamento;
import jakarta.validation.constraints.NotNull;

public record CadastroMetasOrcamentoDTO(
        @NotNull(message = "Obrigatório informar Categoria da Meta de Orçamento")
        CategoriaMetaOrcamento categoria,
        @NotNull(message = "Obrigatório informar porcentagem da Meta de Orçamento")
        Double porcentagem
) {
}
