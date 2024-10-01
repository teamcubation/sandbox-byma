package com.example.project.exceptions;

public class InversorNoEncontradoException extends Exception {
    private static final String DESCRIPTION = "Inversor no encontrado exception. ";

    public InversorNoEncontradoException(String message) {
        super(DESCRIPTION + message);
    }
}
