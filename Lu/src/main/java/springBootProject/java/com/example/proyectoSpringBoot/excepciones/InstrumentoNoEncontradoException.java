package springBootProject.java.com.example.proyectoSpringBoot.excepciones;

public class InstrumentoNoEncontradoException extends Exception{
    public InstrumentoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}