package br.com.alura.orcamento_domestico.exception;

public class ValidacaoException extends RuntimeException{
    public ValidacaoException(String menssagem){
        super(menssagem);
    }
}
