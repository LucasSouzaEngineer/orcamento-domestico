package br.com.alura.orcamento_domestico.controller;

import br.com.alura.orcamento_domestico.dto.AnoMesDTO;
import br.com.alura.orcamento_domestico.dto.receitaDTO.CadastroReceitaDTO;
import br.com.alura.orcamento_domestico.dto.receitaDTO.DadosAtualizacaoReceitaDTO;
import br.com.alura.orcamento_domestico.dto.receitaDTO.DetalheReceitaDTO;
import br.com.alura.orcamento_domestico.service.ReceitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {
    @Autowired
    private ReceitaService service;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarReceita(@RequestBody @Valid CadastroReceitaDTO cadastroDTO, UriComponentsBuilder uriBuilder){
        var receita = service.cadastrarReceita(cadastroDTO);
        var uri = uriBuilder.path("/receitas/{id}")
                .buildAndExpand(receita.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new DetalheReceitaDTO(receita));
    }

    @GetMapping
    public ResponseEntity<Page<DetalheReceitaDTO>> obterListaReceita(Pageable paginacao){
        var page = service.obterListaReceita(paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity obterReceita(@PathVariable Long id){
        return ResponseEntity.ok(service.obterReceita(id));
    }

    @GetMapping("/mes")
    public ResponseEntity<Page<DetalheReceitaDTO>> obterListaReceitasMes(@RequestBody @Valid AnoMesDTO anoMes, Pageable paginacao){
        var page = service.obterListaReceitaMes(anoMes, paginacao);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarReceita(@RequestBody DadosAtualizacaoReceitaDTO dados){
        var receitaAtualizada = service.atualizar(dados);
        return ResponseEntity.ok(receitaAtualizada);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarReceita(@PathVariable Long id){
        service.deletarReceita(id);
        return ResponseEntity.noContent().build();
    }
}
