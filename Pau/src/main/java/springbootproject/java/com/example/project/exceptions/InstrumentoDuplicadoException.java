package com.example.project.exceptions;

public class InstrumentoDuplicadoException extends Exception {
    private static final String DESCRIPTION = "Instrumento duplicado exception. ";

    public InstrumentoDuplicadoException(String message) {
        super(DESCRIPTION + message);
    }
}
