package consoleApp.excepciones;

public class OpcionInvalidaException extends RuntimeException {
    public OpcionInvalidaException(String message) {
        super(message);
    }
}
