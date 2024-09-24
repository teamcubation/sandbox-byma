package springApp.java.com.example.demo.exceptions;

public class InstrumentoNoEncontradoException extends RuntimeException {
    public InstrumentoNoEncontradoException(String message) {
        super(message);
    }
}
