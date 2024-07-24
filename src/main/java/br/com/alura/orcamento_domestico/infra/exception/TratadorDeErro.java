package br.com.alura.orcamento_domestico.infra.exception;

import br.com.alura.orcamento_domestico.exception.ValidacaoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class TratadorDeErro {

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity tratarErroRegraDeNegocio(ValidacaoException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity tratarErro400(HttpMessageNotReadableException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity tratarErro500(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " +ex.getLocalizedMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErroDadoNaoEncontrado(Exception ex){
        return ResponseEntity.notFound().build();
    }

    //HandlerMethodValidationException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErroArgumentoInvalido(MethodArgumentNotValidException ex){
        Map<String, String> argumentosErro = new HashMap<>();
        ex.getFieldErrors().forEach(erro -> {
            argumentosErro.put(erro.getField(), erro.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(argumentosErro);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity tratarErroRecursoNaoEncontrado(NoResourceFoundException ex){
        return ResponseEntity.badRequest().body("Recurso solicitado não encontrado. Verificar URL informada e Método HTTP, URL: "+ ex.getResourcePath() + " - Método HTTP: " + ex.getHttpMethod());
    }
}
