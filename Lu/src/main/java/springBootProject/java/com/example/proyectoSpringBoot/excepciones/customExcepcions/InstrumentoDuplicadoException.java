package com.example.proyectoSpringBoot.excepciones.customExcepcions;

public class InstrumentoDuplicadoException extends Exception{
    public InstrumentoDuplicadoException(String mensaje) {
        super(mensaje);
    }
}