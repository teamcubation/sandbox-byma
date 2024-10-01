package com.example.project.exceptions;

public class InstrumentoNoEncontradoException extends Exception {
    private static final String DESCRIPTION = "Instrumento no encontrado exception. ";

    public InstrumentoNoEncontradoException(String message) {
        super(DESCRIPTION + message);
    }
}
