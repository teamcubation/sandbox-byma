package com.example.project.exceptions;

import com.example.project.utils.messages.ErrorMessageException;

public class InstrumentoDuplicadoException extends Exception {

    public InstrumentoDuplicadoException(String message) {
        super(ErrorMessageException.INSTRUMENTO_DUPLICADO + message);
    }
}
