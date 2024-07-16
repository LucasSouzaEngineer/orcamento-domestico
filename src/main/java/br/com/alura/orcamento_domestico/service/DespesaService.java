package br.com.alura.orcamento_domestico.service;

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
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Service
public class DespesaService {
    @Autowired
    private DespesaRepository repository;

    public Page<DetalheDespesaDTO> obterListaDespesa(Pageable paginacao) {
        return converteLista(repository.findAll(paginacao));
    }

    public DetalheDespesaDTO obterDespesa(Long id) {
        Optional<Despesa> despesaOptional = repository.findById(id);
        if(despesaOptional.isPresent()){
            Despesa despesa = despesaOptional.get();
            return new DetalheDespesaDTO(despesa);
        }
        return null;
    }

    public void deletarDespesa(Long id) {
        repository.deleteById(id);
    }

    public DetalheDespesaDTO cadastrarDespesa(CadastroDespesaDTO dados) {
        Despesa despesa;
        if (dados.categoria() != null){
            despesa = new Despesa(dados.descricao(), dados.valor(), dados.data(), dados.categoria());
        }else {
            despesa = new Despesa(dados.descricao(), dados.valor(), dados.data());
        }

        repository.save(despesa);
        return new DetalheDespesaDTO(despesa);
    }

    public DetalheDespesaDTO atualizarDespesa(DadosAtualizacaoDespesaDTO dados) {
        var despesa = repository.getReferenceById(dados.id());
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

    public Page<DetalheDespesaDTO> obterListaDespesasMes(int ano, int mes, Pageable paginacao) {
        var inicioMes = LocalDate.of(ano, mes, 1);
        var fimMes = LocalDate.of(ano, mes, YearMonth.of(ano, mes).lengthOfMonth());
        Page<Despesa> despesas = repository.listarPorMes(inicioMes, fimMes, paginacao);
        return converteLista(despesas);
    }

    public BigDecimal obterSomaReceitaMes(LocalDate inicioMes, LocalDate fimMes) {
        return repository.obterSomaDespesaMes(inicioMes, fimMes);
    }

    public List<TotalCategoriaDTO> obterDespesaCategoriaMes(LocalDate inicioMes, LocalDate fimMes) {
        return repository.obterDespesaPorCategoria(inicioMes, fimMes);
    }

    private Page<DetalheDespesaDTO> converteLista(Page<Despesa> despesas){
        return despesas.map(DetalheDespesaDTO::new);
    }
}
