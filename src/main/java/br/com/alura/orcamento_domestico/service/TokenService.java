package br.com.alura.orcamento_domestico.service;

import br.com.alura.orcamento_domestico.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {


    public String gerarToken(Usuario usuario){
        try {
            Algorithm algoritimo = Algorithm.HMAC256("123455678");
            return JWT.create()
                    .withIssuer("API Orçamento Domestico")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritimo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("erro ao gerar token jwt", exception);
        }
    }

    public String verificarEObterLogin(String tokenJWT){
        try {
            Algorithm algoritimo = Algorithm.HMAC256("123455678");
            return JWT.require(algoritimo)
                    .withIssuer("API Orçamento Domestico")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token inválido ou expirado!");
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2l).toInstant(ZoneOffset.of("-03:00"));
    }
}
