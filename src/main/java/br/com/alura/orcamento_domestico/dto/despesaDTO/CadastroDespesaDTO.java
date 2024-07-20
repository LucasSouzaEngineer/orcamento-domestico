package br.com.alura.orcamento_domestico.dto.despesaDTO;

import br.com.alura.orcamento_domestico.model.CategoriaDespesa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CadastroDespesaDTO(
        @NotBlank
        String descricao,
        @NotNull
        BigDecimal valor,
        @NotNull
        LocalDate data,
        @NotNull
        CategoriaDespesa categoria //Usado apenas para despesa
){
}
