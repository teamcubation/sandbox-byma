package springApp.java.com.example.demo.exceptions;

public class InvalidInstrumentoDataException extends RuntimeException {
    public InvalidInstrumentoDataException(String message) {
        super(message);
    }
}
