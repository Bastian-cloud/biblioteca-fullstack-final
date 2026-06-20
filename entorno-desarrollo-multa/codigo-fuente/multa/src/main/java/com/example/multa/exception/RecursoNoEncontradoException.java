package com.example.multa.exception;

public class RecursoNoEncontradoException
        extends RuntimeException {

    public RecursoNoEncontradoException(
            String mensaje) {

        super(mensaje);
    }
}