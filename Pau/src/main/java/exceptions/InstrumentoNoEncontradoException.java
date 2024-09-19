package model.exceptions;

public class InstrumentoNoEncontradoException extends Exception {
    public InstrumentoNoEncontradoException() {
        super();
    }

    public InstrumentoNoEncontradoException(String message) {
        super(message);
    }
}
