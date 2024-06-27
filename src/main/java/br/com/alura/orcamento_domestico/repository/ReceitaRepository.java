package br.com.alura.orcamento_domestico.repository;

import br.com.alura.orcamento_domestico.model.Receita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    @Query("SELECT r FROM Receita r WHERE r.data BETWEEN :inicioMes AND :fimMes")
    Page<Receita> listarPorMes(LocalDate inicioMes, LocalDate fimMes, Pageable paginacao);
    @Query("SELECT SUM(r.valor) FROM Receita r WHERE r.data BETWEEN :inicioMes AND :fimMes")
    BigDecimal obterSomaReceitaMes(LocalDate inicioMes, LocalDate fimMes);
}
