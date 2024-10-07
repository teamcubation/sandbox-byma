package springbootApp.app.exceptions;

public class DniInvalidoException extends RuntimeException {
    public DniInvalidoException(String message) {
        super(message);
    }
}
