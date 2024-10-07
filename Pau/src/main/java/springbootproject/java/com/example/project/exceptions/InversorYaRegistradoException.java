package com.example.project.exceptions;

import com.example.project.utils.messages.ErrorMessageException;

public class InversorYaRegistradoException extends Exception {

    public InversorYaRegistradoException(String message) {
        super(ErrorMessageException.INVERSOR_YA_REGISTRADO + message);
    }
}
