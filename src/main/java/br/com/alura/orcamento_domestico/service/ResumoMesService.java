package br.com.alura.orcamento_domestico.service;

import br.com.alura.orcamento_domestico.dto.AnoMesDTO;
import br.com.alura.orcamento_domestico.dto.resumoMesDTO.ResumoMesDTO;
import br.com.alura.orcamento_domestico.dto.resumoMesDTO.TotalCategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ResumoMesService {
    @Autowired
    private ReceitaService receitaService;
    @Autowired
    private DespesaService despesaService;
    @Autowired
    private ConversaoDateService conversaoDateService;

    public ResumoMesDTO obterResumoMes(AnoMesDTO anoMes) {
        var inicioMes = conversaoDateService.obterInicioMes(anoMes);
        var fimMes = conversaoDateService.obterFimMes(anoMes);
        BigDecimal totalReceitaMes = receitaService.obterSomaReceitaMes(inicioMes, fimMes);
        BigDecimal totalDespesaMes = despesaService.obterSomaReceitaMes(inicioMes, fimMes);
        List<TotalCategoriaDTO> listaDespesaCategoriaMes = despesaService.obterDespesaCategoriaMes(inicioMes, fimMes);
        return new ResumoMesDTO(totalReceitaMes, totalDespesaMes, listaDespesaCategoriaMes);
    }
}
