package br.edu.unime.api2.service.exceptions;

public class CPFNotUpdateException extends RuntimeException {
    public CPFNotUpdateException(String mensagem) {
        super(mensagem);
    }
}
