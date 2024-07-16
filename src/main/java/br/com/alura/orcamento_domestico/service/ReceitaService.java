package br.com.alura.orcamento_domestico.service;

import br.com.alura.orcamento_domestico.dto.receitaDTO.CadastroReceitaDTO;
import br.com.alura.orcamento_domestico.dto.receitaDTO.DadosAtualizacaoReceitaDTO;
import br.com.alura.orcamento_domestico.dto.receitaDTO.DetalheReceitaDTO;
import br.com.alura.orcamento_domestico.model.Receita;
import br.com.alura.orcamento_domestico.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Optional;

@Service
public class ReceitaService {
    @Autowired
    private ReceitaRepository repository;

    public Page<DetalheReceitaDTO> obterListaReceita(Pageable paginacao) {
        return converteLista(repository.findAll(paginacao));
    }

    public DetalheReceitaDTO obterReceita(Long id) {
        Optional<Receita> receitaOptional = repository.findById(id);
        if (receitaOptional.isPresent()){
            Receita receita = receitaOptional.get();
            return new DetalheReceitaDTO(receita);
        }
        return null;
    }

    public void deletarReceita(Long id) {
        repository.deleteById(id);
    }

    public Receita cadastrarReceita(CadastroReceitaDTO cadastroDTO) {
        Receita receita = new Receita(cadastroDTO.descricao(), cadastroDTO.valor(), cadastroDTO.data());
        repository.save(receita);
        return receita;
    }
    public DetalheReceitaDTO atualizar(DadosAtualizacaoReceitaDTO dados) {
        var receita = repository.getReferenceById(dados.id());
        if (dados.descricao() != null){
            receita.setDescricao(dados.descricao());
        }
        if (dados.valor() != null){
            receita.setValor(dados.valor());
        }
        if (dados.data() != null){
            receita.setData(dados.data());
        }
        return new DetalheReceitaDTO(receita);
    }

    public Page<DetalheReceitaDTO> obterListaReceitaMes(int ano, int mes, Pageable paginacao) {
        LocalDate inicioMes = LocalDate.of(ano, mes, 1);
        LocalDate fimMes = LocalDate.of(ano, mes, YearMonth.of(ano, mes).lengthOfMonth());
        return converteLista(repository.listarPorMes(inicioMes, fimMes, paginacao));
    }

    public BigDecimal obterSomaReceitaMes(LocalDate inicioMes, LocalDate fimMes) {
        return repository.obterSomaReceitaMes(inicioMes, fimMes);
    }

    private Page<DetalheReceitaDTO> converteLista(Page<Receita> receitas){
       return receitas.map(DetalheReceitaDTO::new);
    }

}
