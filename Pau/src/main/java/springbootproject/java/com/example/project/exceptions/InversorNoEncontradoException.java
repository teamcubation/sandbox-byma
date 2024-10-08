package com.example.project.exceptions;

import com.example.project.utils.messages.ErrorMessageException;

public class InversorNoEncontradoException extends Exception {

    public InversorNoEncontradoException(String message) {
        super(ErrorMessageException.INVERSOR_NO_ENCONTRADO + message);
    }
}
