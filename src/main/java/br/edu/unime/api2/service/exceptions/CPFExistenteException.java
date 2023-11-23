package br.edu.unime.api2.service.exceptions;

public class CPFExistenteException extends RuntimeException {
    public CPFExistenteException(String mensagem) {
        super(mensagem);
    }
}


