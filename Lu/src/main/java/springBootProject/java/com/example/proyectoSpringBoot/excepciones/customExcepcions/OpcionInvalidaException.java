package com.example.proyectoSpringBoot.excepciones.customExcepcions;

public class OpcionInvalidaException extends Exception {
    public OpcionInvalidaException(String message) {
        super(message);
    }
}
