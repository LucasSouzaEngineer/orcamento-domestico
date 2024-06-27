package br.com.alura.orcamento_domestico.dto.resumoMesDTO;

import br.com.alura.orcamento_domestico.model.CategoriaDespesa;

import java.math.BigDecimal;

public record TotalCategoriaDTO(
        CategoriaDespesa categoria,
        BigDecimal valor
) {
}
