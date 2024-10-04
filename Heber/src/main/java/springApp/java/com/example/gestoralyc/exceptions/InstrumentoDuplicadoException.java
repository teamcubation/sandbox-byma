package springApp.java.com.example.gestoralyc.exceptions;

public class InstrumentoDuplicadoException extends RuntimeException {
    private static final String MSG_ERROR_INSTRUMENTO_EXISTENTE = "El instrumento con nombre %s ya existe";

    public InstrumentoDuplicadoException(String nombreInstrumento) {
        super(String.format(MSG_ERROR_INSTRUMENTO_EXISTENTE, nombreInstrumento));
    }
}
