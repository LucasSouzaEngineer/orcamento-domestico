package br.com.alura.orcamento_domestico.controller;

import br.com.alura.orcamento_domestico.dto.AnoMesDTO;
import br.com.alura.orcamento_domestico.service.ResumoMesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resumo")
public class ResumoMesController {
    @Autowired
    private ResumoMesService service;


    @GetMapping
    public ResponseEntity obterResumoMes(@RequestBody @Valid AnoMesDTO anoMes){
        var resumoMes = service.obterResumoMes(anoMes);
        return ResponseEntity.ok(resumoMes);
    }
}
