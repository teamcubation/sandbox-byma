package consoleApp.excepciones;

public class InstrumentoDuplicadoException extends Exception{
    public InstrumentoDuplicadoException(String mensaje) {
        super(mensaje);
    }
}