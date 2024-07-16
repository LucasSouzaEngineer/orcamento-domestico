package br.com.alura.orcamento_domestico.dto.metasOrcamentoDTO;

import br.com.alura.orcamento_domestico.model.CategoriaMetaOrcamento;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ListaCadastroMetasOrcamentoDTO(

       List<@Valid CadastroMetasOrcamentoDTO> metas
) {
}
