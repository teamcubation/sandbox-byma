package springBootProject.java.com.example.proyectoSpringBoot.excepciones;

public class OpcionInvalidaException extends RuntimeException {
    public OpcionInvalidaException(String message) {
        super(message);
    }
}
