package Excepciones;

public class InstrumentoNoEncontradoException extends Exception{
    public InstrumentoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}