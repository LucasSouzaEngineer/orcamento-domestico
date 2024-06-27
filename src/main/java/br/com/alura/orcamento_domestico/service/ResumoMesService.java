package br.com.alura.orcamento_domestico.service;

import br.com.alura.orcamento_domestico.dto.resumoMesDTO.ResumoMesDTO;
import br.com.alura.orcamento_domestico.dto.resumoMesDTO.TotalCategoriaDTO;
import br.com.alura.orcamento_domestico.model.Despesa;
import br.com.alura.orcamento_domestico.repository.DespesaRepository;
import br.com.alura.orcamento_domestico.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class ResumoMesService {
    @Autowired
    private ReceitaRepository receitaRepository;
    @Autowired
    private DespesaRepository despesaRepository;

    public ResumoMesDTO obterResumoMes(int ano, int mes) {
        var inicioMes = LocalDate.of(ano, mes, 1);
        var fimMes = LocalDate.of(ano, mes, YearMonth.of(ano, mes).lengthOfMonth());
        BigDecimal totalReceita = receitaRepository.obterSomaReceitaMes(inicioMes, fimMes);
        BigDecimal totalDespesa = despesaRepository.obterSomaDespesaMes(inicioMes, fimMes);
        var despesasPorCategoria = despesaRepository.obterDespesaPorCategoria(inicioMes, fimMes);
        return new ResumoMesDTO(totalReceita, totalDespesa, despesasPorCategoria);
    }
}
