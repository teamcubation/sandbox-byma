package consoleApp.excepciones;

public class InstrumentoNoEncontradoException extends RuntimeException {
    public InstrumentoNoEncontradoException(String message) {
        super(message);
    }
}
