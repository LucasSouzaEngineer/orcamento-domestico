package br.com.alura.orcamento_domestico.controller;

import br.com.alura.orcamento_domestico.service.ResumoMesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resumo")
public class ResumoMesController {
    @Autowired
    private ResumoMesService service;

    @GetMapping("/{ano}/{mes}")
    public ResponseEntity obterResumoMes(@PathVariable int ano, @PathVariable int mes){
        var resumoMes = service.obterResumoMes(ano, mes);
        return ResponseEntity.ok(resumoMes);
    }
}
