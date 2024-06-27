package br.com.alura.orcamento_domestico.repository;

import br.com.alura.orcamento_domestico.dto.resumoMesDTO.TotalCategoriaDTO;
import br.com.alura.orcamento_domestico.model.CategoriaDespesa;
import br.com.alura.orcamento_domestico.model.Despesa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    @Query("SELECT d FROM Despesa d WHERE d.data BETWEEN :inicioMes AND :fimMes")
    Page<Despesa> listarPorMes(LocalDate inicioMes, LocalDate fimMes, Pageable paginacao);

    @Query("SELECT SUM(d.valor) FROM Despesa d WHERE d.data BETWEEN :inicioMes AND :fimMes")
    BigDecimal obterSomaDespesaMes(LocalDate inicioMes, LocalDate fimMes);

    @Query("SELECT NEW br.com.alura.orcamento_domestico.dto.resumoMesDTO.TotalCategoriaDTO(d.categoria, SUM(d.valor)) FROM Despesa d WHERE d.data BETWEEN :inicioMes AND :fimMes group by d.categoria")
    List<TotalCategoriaDTO> obterDespesaPorCategoria(LocalDate inicioMes, LocalDate fimMes);
}
