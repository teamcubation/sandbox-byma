package com.example.project.exceptions;

import com.example.project.utils.messages.ErrorMessageException;

public class InstrumentoNoEncontradoException extends Exception {

    public InstrumentoNoEncontradoException(String message) {
        super(ErrorMessageException.INSTRUMENTO_NO_ENCONTRADO + message);
    }
}
