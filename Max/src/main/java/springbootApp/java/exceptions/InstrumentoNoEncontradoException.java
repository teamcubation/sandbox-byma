package springbootApp.java.exceptions;

public class InstrumentoNoEncontradoException extends RuntimeException {
    public InstrumentoNoEncontradoException(String message) {
        super(message);
    }
}
