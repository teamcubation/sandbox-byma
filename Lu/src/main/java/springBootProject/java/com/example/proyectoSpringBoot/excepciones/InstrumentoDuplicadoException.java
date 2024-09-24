package springBootProject.java.com.example.proyectoSpringBoot.excepciones;

public class InstrumentoDuplicadoException extends Exception{
    public InstrumentoDuplicadoException(String mensaje) {
        super(mensaje);
    }
}