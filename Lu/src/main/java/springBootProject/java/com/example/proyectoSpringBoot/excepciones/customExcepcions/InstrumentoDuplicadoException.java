package com.example.proyectoSpringBoot.excepciones.customExcepcions;

public class InstrumentoDuplicadoException extends RuntimeException{
    public InstrumentoDuplicadoException(String mensaje) {
        super(mensaje);
    }
}