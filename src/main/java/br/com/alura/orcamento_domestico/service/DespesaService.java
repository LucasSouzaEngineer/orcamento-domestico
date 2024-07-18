package br.com.alura.orcamento_domestico.service;

import br.com.alura.orcamento_domestico.dto.AnoMesDTO;
import br.com.alura.orcamento_domestico.dto.despesaDTO.CadastroDespesaDTO;
import br.com.alura.orcamento_domestico.dto.despesaDTO.DadosAtualizacaoDespesaDTO;
import br.com.alura.orcamento_domestico.dto.despesaDTO.DetalheDespesaDTO;
import br.com.alura.orcamento_domestico.dto.resumoMesDTO.TotalCategoriaDTO;
import br.com.alura.orcamento_domestico.model.Despesa;
import br.com.alura.orcamento_domestico.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DespesaService {
    @Autowired
    private DespesaRepository despesaRepository;
    @Autowired
    private ConversaoDateService conversaoDateService;

    public Page<DetalheDespesaDTO> obterListaDespesa(Pageable paginacao) {
        return converteLista(despesaRepository.findAll(paginacao));
    }

    public DetalheDespesaDTO obterDespesa(Long id) {
        Optional<Despesa> despesaOptional = despesaRepository.findById(id);
        if(despesaOptional.isPresent()){
            Despesa despesa = despesaOptional.get();
            return new DetalheDespesaDTO(despesa);
        }
        return null;
    }

    public void deletarDespesa(Long id) {
        despesaRepository.deleteById(id);
    }

    public DetalheDespesaDTO cadastrarDespesa(CadastroDespesaDTO dados) {
        Despesa despesa = new Despesa(dados);
        despesaRepository.save(despesa);
        return new DetalheDespesaDTO(despesa);
    }

    public DetalheDespesaDTO atualizarDespesa(DadosAtualizacaoDespesaDTO dados) {
        var despesa = despesaRepository.getReferenceById(dados.id());
        if (dados.descricao() != null){
            despesa.setDescricao(dados.descricao());
        }
        if (dados.valor() != null){
            despesa.setValor(dados.valor());
        }
        if (dados.data() != null){
            despesa.setData(dados.data());
        }
        if (dados.categoria() != null){
            despesa.setCategoria(dados.categoria());
        }
        return new DetalheDespesaDTO(despesa);
    }

    public Page<DetalheDespesaDTO> obterListaDespesasMes(AnoMesDTO anoMes, Pageable paginacao) {
        LocalDate inicioMes = conversaoDateService.obterInicioMes(anoMes);
        LocalDate fimMes = conversaoDateService.obterFimMes(anoMes);
        Page<Despesa> despesas = despesaRepository.listarPorMes(inicioMes, fimMes, paginacao);
        return converteLista(despesas);
    }

    public BigDecimal obterSomaReceitaMes(LocalDate inicioMes, LocalDate fimMes) {
        return despesaRepository.obterSomaDespesaMes(inicioMes, fimMes);
    }

    public List<TotalCategoriaDTO> obterDespesaCategoriaMes(LocalDate inicioMes, LocalDate fimMes) {
        return despesaRepository.obterDespesaPorCategoria(inicioMes, fimMes);
    }

    private Page<DetalheDespesaDTO> converteLista(Page<Despesa> despesas){
        return despesas.map(DetalheDespesaDTO::new);
    }
}
