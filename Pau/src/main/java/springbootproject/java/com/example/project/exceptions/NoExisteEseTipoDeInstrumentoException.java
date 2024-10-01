package com.example.project.exceptions;

public class NoExisteEseTipoDeInstrumentoException extends Exception {
    private static final String DESCRIPTION = "No existe tipo de instrumento exception. ";

    public NoExisteEseTipoDeInstrumentoException(String message) {
        super(DESCRIPTION + message);
    }
}
