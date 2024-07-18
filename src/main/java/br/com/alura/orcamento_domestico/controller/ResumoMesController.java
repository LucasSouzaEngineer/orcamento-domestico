package br.com.alura.orcamento_domestico.controller;

import br.com.alura.orcamento_domestico.dto.resumoMesDTO.SolicitacaoResumoMesDTO;
import br.com.alura.orcamento_domestico.service.ResumoMesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resumo")
public class ResumoMesController {
    @Autowired
    private ResumoMesService service;

//    @GetMapping("/{ano}/{mes}")
//    public ResponseEntity obterResumoMes(@PathVariable int ano, @PathVariable int mes){
//        var resumoMes = service.obterResumoMes(ano, mes);
//        return ResponseEntity.ok(resumoMes);
//    }

    @GetMapping
    public ResponseEntity obterResumoMes(@RequestBody SolicitacaoResumoMesDTO dados){
        var resumoMes = service.obterResumoMes(dados);
        return ResponseEntity.ok(resumoMes);
    }
}
