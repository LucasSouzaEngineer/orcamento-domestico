package br.com.alura.orcamento_domestico.dto.despesaDTO;

import br.com.alura.orcamento_domestico.model.CategoriaDespesa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CadastroDespesaDTO(
        @NotBlank(message = "Obrigatório informar descrição")
        String descricao,
        @NotNull(message = "Obrigatório informar valor")
        BigDecimal valor,
        @NotNull(message = "Obrigatório informar data")
        LocalDate data,
        @NotNull(message = "Obrigatório informar Categoria")
        CategoriaDespesa categoria //Usado apenas para despesa
){
}
