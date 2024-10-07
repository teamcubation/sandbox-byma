package com.example.project.exceptions;

import com.example.project.utils.messages.ErrorMessageException;

public class NoExisteEseTipoDeInstrumentoException extends Exception {

    public NoExisteEseTipoDeInstrumentoException(String message) {
        super(ErrorMessageException.NO_EXISTE_TIPO_INSTRUMENTO + message);
    }
}
