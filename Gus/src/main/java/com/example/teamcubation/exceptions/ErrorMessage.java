package com.example.teamcubation.exceptions;

import lombok.Data;

@Data
public class ErrorMessage {

    private String message;
    private String exception;
    private String path;

    public ErrorMessage(Exception exception, String path) {
        this.message = exception.getMessage();
        this.exception = exception.getClass().getSimpleName();
        this.path = path;
    }
}
