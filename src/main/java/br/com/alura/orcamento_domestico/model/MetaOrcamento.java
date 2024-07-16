package br.com.alura.orcamento_domestico.model;

import br.com.alura.orcamento_domestico.dto.metasOrcamentoDTO.CadastroMetasOrcamentoDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "metas_orcamento")
public class MetaOrcamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CategoriaMetaOrcamento categoria;

    private Double porcentagem;

    public MetaOrcamento(){}

    public MetaOrcamento(CadastroMetasOrcamentoDTO dados){
        this.categoria = dados.categoria();
        this.porcentagem = dados.porcentagem();
    }

    public Long getId() {
        return id;
    }

    public CategoriaMetaOrcamento getCategoria() {
        return categoria;
    }

    public Double getPorcentagem() {
        return porcentagem;
    }
}
