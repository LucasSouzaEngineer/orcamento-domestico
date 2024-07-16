package br.com.alura.orcamento_domestico.repository;

import br.com.alura.orcamento_domestico.model.MetaOrcamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaOrcamentoRepository extends JpaRepository<MetaOrcamento, Long> {
}
