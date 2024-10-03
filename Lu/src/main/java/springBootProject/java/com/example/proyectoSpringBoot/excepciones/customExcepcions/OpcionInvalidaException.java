package com.example.proyectoSpringBoot.excepciones.customExcepcions;

public class OpcionInvalidaException extends RuntimeException {
    public OpcionInvalidaException(String message) {
        super(message);
    }
}
