package com.example.project.exceptions;

public class InversorYaRegistradoException extends Exception {
    private static final String DESCRIPTION = "Inversor ya registrado exception. ";

    public InversorYaRegistradoException(String message) {
        super(DESCRIPTION + message);
    }
}
