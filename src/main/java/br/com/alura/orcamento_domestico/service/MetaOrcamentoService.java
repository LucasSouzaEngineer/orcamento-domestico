package br.com.alura.orcamento_domestico.service;

import br.com.alura.orcamento_domestico.dto.metasOrcamentoDTO.CadastroMetasOrcamentoDTO;
import br.com.alura.orcamento_domestico.dto.metasOrcamentoDTO.DetalheMetaOrcamentoDTO;
import br.com.alura.orcamento_domestico.exception.ValidacaoException;
import br.com.alura.orcamento_domestico.model.MetaOrcamento;
import br.com.alura.orcamento_domestico.repository.MetaOrcamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MetaOrcamentoService {
    @Autowired
    private MetaOrcamentoRepository metaOrcamentoRepository;

    public List<DetalheMetaOrcamentoDTO> cadastrar(List<CadastroMetasOrcamentoDTO> dados) throws ValidacaoException{
        double somaPorcentagem = dados.stream().map(CadastroMetasOrcamentoDTO::porcentagem).reduce( 0.0, Double::sum);
        if (somaPorcentagem != 100.0){
            throw new ValidacaoException("Soma das metas deve ser igual a 100%");
        }

        List<MetaOrcamento> metasOrcamento = converterListaDtoParaMetas(dados);
        metaOrcamentoRepository.deleteAll();
        metasOrcamento.forEach(metaOrcamento -> metaOrcamentoRepository.save(metaOrcamento));
        return converterListaMetasParaDto(metaOrcamentoRepository.findAll());
    }

    public List<DetalheMetaOrcamentoDTO> listarMetasOrcamento() {
        return converterListaMetasParaDto(metaOrcamentoRepository.findAll());
    }

    public List<MetaOrcamento> converterListaDtoParaMetas(List<CadastroMetasOrcamentoDTO> dados){
        return dados.stream()
                .map(MetaOrcamento::new)
                .collect(Collectors.toList());
    }

    public List<DetalheMetaOrcamentoDTO> converterListaMetasParaDto(List<MetaOrcamento> dados){
        return dados.stream()
                .map(DetalheMetaOrcamentoDTO::new)
                .collect(Collectors.toList());
    }
}
