package br.com.alura.orcamento_domestico.dto.receitaDTO;

import br.com.alura.orcamento_domestico.dto.DadosDataDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CadastroReceitaDTO(
        @NotBlank
        String descricao,
        @NotNull
        BigDecimal valor,
        @NotNull
        DadosDataDTO data
){
}
