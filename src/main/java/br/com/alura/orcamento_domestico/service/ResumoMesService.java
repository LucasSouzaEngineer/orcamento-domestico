package br.com.alura.orcamento_domestico.service;

import br.com.alura.orcamento_domestico.dto.resumoMesDTO.ResumoMesDTO;
import br.com.alura.orcamento_domestico.dto.resumoMesDTO.SolicitacaoResumoMesDTO;
import br.com.alura.orcamento_domestico.dto.resumoMesDTO.TotalCategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class ResumoMesService {
    @Autowired
    private ReceitaService receitaService;
    @Autowired
    private DespesaService despesaService;

    public ResumoMesDTO obterResumoMes(SolicitacaoResumoMesDTO dados) {
        var inicioMes = LocalDate.of(dados.anoMes().getYear(), dados.anoMes().getMonth(), 1);
        var fimMes = LocalDate.of(dados.anoMes().getYear(), dados.anoMes().getMonth(), dados.anoMes().lengthOfMonth());
        BigDecimal totalReceitaMes = receitaService.obterSomaReceitaMes(inicioMes, fimMes);
        BigDecimal totalDespesaMes = despesaService.obterSomaReceitaMes(inicioMes, fimMes);
        List<TotalCategoriaDTO> listaDespesaCategoriaMes = despesaService.obterDespesaCategoriaMes(inicioMes, fimMes);
        return new ResumoMesDTO(totalReceitaMes, totalDespesaMes, listaDespesaCategoriaMes);
    }
}
