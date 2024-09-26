package springApp.java.com.example.gestoralyc.exceptions;

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
        return new ResponseEntity<>("Tipo de instrumento no válido", HttpStatus.BAD_REQUEST);
    }

    // Manejo de InstrumentoDuplicadoException
    @ExceptionHandler(InstrumentoDuplicadoException.class)
    public ResponseEntity<String> handleInstrumentoDuplicadoException(InstrumentoDuplicadoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    // Manejo de InstrumentoNoEncontradoException
    @ExceptionHandler(InstrumentoNoEncontradoException.class)
    public ResponseEntity<String> handleInstrumentoNoEncontradoException(InstrumentoNoEncontradoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidInstrumentoDataException.class)
    public ResponseEntity<String> handleInvalidInstrumentoDataException(InvalidInstrumentoDataException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


    // puedo seguir agregando metodos para todas las excepciones que quiera manejar
}
