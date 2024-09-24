package springApp.java.com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>("Tipo de instrumento no válido", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(com.fasterxml.jackson.databind.exc.InvalidTypeIdException.class)
    public ResponseEntity<String> handleInvalidTypeIdException(com.fasterxml.jackson.databind.exc.InvalidTypeIdException ex) {
        // Retornamos un mensaje más amigable sin la información técnica de la excepción
        return new ResponseEntity<>("Tipo de instrumento no válido", HttpStatus.BAD_REQUEST);
    }

    // Puedes agregar más métodos para manejar otras excepciones si es necesario
}