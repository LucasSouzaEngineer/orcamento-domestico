package br.com.alura.orcamento_domestico.dto.despesaDTO;

import br.com.alura.orcamento_domestico.dto.DadosDataDTO;
import br.com.alura.orcamento_domestico.model.CategoriaDespesa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CadastroDespesaDTO(
        @NotBlank
        String descricao,
        @NotNull
        BigDecimal valor,
        @NotNull
        DadosDataDTO data,
        CategoriaDespesa categoria //Usado apenas para despesa
){
}
