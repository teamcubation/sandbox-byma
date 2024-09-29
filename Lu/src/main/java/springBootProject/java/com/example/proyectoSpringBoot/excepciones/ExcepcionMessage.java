package com.example.proyectoSpringBoot.excepciones;

import lombok.Data;

@Data
public class ExcepcionMessage {
    private String exception;
    private String message;
    private String path;

    public ExcepcionMessage(String path, Exception exception) {
        this.exception = exception.getClass().getSimpleName();
        this.message = exception.getMessage();
        this.path = path;
    }
}
