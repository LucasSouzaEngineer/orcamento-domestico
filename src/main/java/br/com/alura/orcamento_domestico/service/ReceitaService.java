package br.com.alura.orcamento_domestico.service;

import br.com.alura.orcamento_domestico.dto.AnoMesDTO;
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

@Service
public class ReceitaService {
    @Autowired
    private ReceitaRepository receitaRepository;
    @Autowired
    private ConversaoDateService conversaoDateService;

    public Page<DetalheReceitaDTO> obterListaReceita(Pageable paginacao) {
        return converteLista(receitaRepository.findAll(paginacao));
    }

    public DetalheReceitaDTO obterReceita(Long id) {

        Receita receita = receitaRepository.getReferenceById(id);
        return new DetalheReceitaDTO(receita);

    }

    public void deletarReceita(Long id) {
        receitaRepository.deleteById(id);
    }

    public Receita cadastrarReceita(CadastroReceitaDTO cadastroDTO) {
        Receita receita = new Receita(cadastroDTO.descricao(), cadastroDTO.valor(), cadastroDTO.data());
        receitaRepository.save(receita);
        return receita;
    }
    public DetalheReceitaDTO atualizar(DadosAtualizacaoReceitaDTO dados) {
        var receita = receitaRepository.getReferenceById(dados.id());
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

    public Page<DetalheReceitaDTO> obterListaReceitaMes(AnoMesDTO anoMes, Pageable paginacao) {
        LocalDate inicioMes = conversaoDateService.obterInicioMes(anoMes);
        LocalDate fimMes = conversaoDateService.obterFimMes(anoMes);
        return converteLista(receitaRepository.listarPorMes(inicioMes, fimMes, paginacao));
    }

    public BigDecimal obterSomaReceitaMes(LocalDate inicioMes, LocalDate fimMes) {
        return receitaRepository.obterSomaReceitaMes(inicioMes, fimMes);
    }

    private Page<DetalheReceitaDTO> converteLista(Page<Receita> receitas){
       return receitas.map(DetalheReceitaDTO::new);
    }

}
