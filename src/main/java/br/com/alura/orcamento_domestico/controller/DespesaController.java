package br.com.alura.orcamento_domestico.controller;

import br.com.alura.orcamento_domestico.dto.despesaDTO.CadastroDespesaDTO;
import br.com.alura.orcamento_domestico.dto.despesaDTO.DadosAtualizacaoDespesaDTO;
import br.com.alura.orcamento_domestico.dto.despesaDTO.DetalheDespesaDTO;
import br.com.alura.orcamento_domestico.service.DespesaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/despesas")
public class DespesaController {
    @Autowired
    private DespesaService service;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarDespesa(@RequestBody @Valid CadastroDespesaDTO dados, UriComponentsBuilder uriBuilder){
        var despesaDetalhe = service.cadastrarDespesa(dados);
        var uri = uriBuilder.path("/despesas/{id}")
                .buildAndExpand(despesaDetalhe.id())
                .toUri();

        return ResponseEntity.created(uri).body(despesaDetalhe);
    }

    @GetMapping
    public ResponseEntity<Page<DetalheDespesaDTO>> obterListaDespesas(Pageable paginacao){
        var page = service.obterListaDespesa(paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public DetalheDespesaDTO obterDespesa(@PathVariable Long id){
        return service.obterDespesa(id);
    }

    @GetMapping("/{ano}/{mes}")
    public ResponseEntity<Page<DetalheDespesaDTO>> obterListaDespesaMes(@PathVariable int ano, @PathVariable int mes, Pageable paginacao){
        var page = service.obterListaDespesasMes(ano, mes, paginacao);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarDespesa(@RequestBody DadosAtualizacaoDespesaDTO dados){
        DetalheDespesaDTO despesaAtualizada = service.atualizarDespesa(dados);
        return ResponseEntity.ok(despesaAtualizada);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarDespesa(@PathVariable Long id){
        service.deletarDespesa(id);
        return ResponseEntity.noContent().build();
    }
}
