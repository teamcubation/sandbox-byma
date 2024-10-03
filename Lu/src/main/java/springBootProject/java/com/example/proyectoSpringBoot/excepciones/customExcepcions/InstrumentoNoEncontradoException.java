package com.example.proyectoSpringBoot.excepciones.customExcepcions;

public class InstrumentoNoEncontradoException extends RuntimeException {
    public InstrumentoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}