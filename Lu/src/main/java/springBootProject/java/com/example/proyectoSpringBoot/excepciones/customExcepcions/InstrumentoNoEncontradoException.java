package com.example.proyectoSpringBoot.excepciones.customExcepcions;

public class InstrumentoNoEncontradoException extends Exception{
    public InstrumentoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}