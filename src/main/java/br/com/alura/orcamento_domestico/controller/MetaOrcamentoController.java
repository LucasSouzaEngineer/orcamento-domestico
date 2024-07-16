package br.com.alura.orcamento_domestico.controller;

import br.com.alura.orcamento_domestico.dto.metasOrcamentoDTO.ListaCadastroMetasOrcamentoDTO;
import br.com.alura.orcamento_domestico.dto.metasOrcamentoDTO.DetalheMetaOrcamentoDTO;
import br.com.alura.orcamento_domestico.service.MetaOrcamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/metaorcamento")
public class MetaOrcamentoController {
    @Autowired
    private MetaOrcamentoService metaOrcamentoService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarMetasOrcamento(@RequestBody @Valid ListaCadastroMetasOrcamentoDTO dados){
        List<DetalheMetaOrcamentoDTO> metasCadastradas = metaOrcamentoService.cadastrar(dados);
        return ResponseEntity.ok().body(metasCadastradas);
    }

    @GetMapping
    public ResponseEntity<List<DetalheMetaOrcamentoDTO>> obterListaMetasOrcamento(){
        List<DetalheMetaOrcamentoDTO> listaMetaOrcamento = metaOrcamentoService.listarMetasOrcamento();
        return ResponseEntity.ok(listaMetaOrcamento);
    }
}
