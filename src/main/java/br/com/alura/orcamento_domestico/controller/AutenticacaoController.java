package br.com.alura.orcamento_domestico.controller;

import br.com.alura.orcamento_domestico.dto.autenticacao.AutenticacaoDTO;
import br.com.alura.orcamento_domestico.dto.autenticacao.TokenJWTDTO;
import br.com.alura.orcamento_domestico.model.Usuario;
import br.com.alura.orcamento_domestico.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody AutenticacaoDTO dadosAutenticacao){
        var tokenValidacao = new UsernamePasswordAuthenticationToken(dadosAutenticacao.login(), dadosAutenticacao.senha());
        var autenticacao = manager.authenticate(tokenValidacao);
        var tokenJWT = tokenService.gerarToken((Usuario) autenticacao.getPrincipal());

        return ResponseEntity.ok(new TokenJWTDTO(tokenJWT));
    }
}
