package springApp.java.com.example.gestoralyc.exceptions;

public class InstrumentoNoEncontradoException extends RuntimeException {
    private static final String MSG_ERROR_INSTRUMENTO_NO_ENCONTRADO = "El instrumento con id %s no fue encontrado";

    public InstrumentoNoEncontradoException(Long id) {
        super(String.valueOf(id));
    }
}
