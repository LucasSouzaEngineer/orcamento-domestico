package br.com.alura.orcamento_domestico.model;

import br.com.alura.orcamento_domestico.dto.DadosDataDTO;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Table(name = "despesas")
public class Despesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
    @Enumerated(EnumType.STRING)
    private CategoriaDespesa categoria = CategoriaDespesa.OUTRAS;

    public Despesa() {
    }

    public Despesa(String descricao, BigDecimal valor, DadosDataDTO data) {
        this.descricao = descricao;
        this.valor = valor;
        this.setData(data);
    }

    public Despesa(String descricao, BigDecimal valor, DadosDataDTO data, CategoriaDespesa categoria) {
        this.descricao = descricao;
        this.valor = valor;
        this.setData(data);
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setData(DadosDataDTO data) {
        this.data = LocalDate.of(data.ano(), data.mes(), data.dia());
    }

    public CategoriaDespesa getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDespesa categoria) {
        this.categoria = categoria;
    }
}
