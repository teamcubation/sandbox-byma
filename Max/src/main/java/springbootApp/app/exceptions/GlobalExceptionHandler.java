package  springbootApp.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(InstrumentoDuplicadoException.class)
    public ResponseEntity<String> handleInstrumentoDuplicadoException(InstrumentoDuplicadoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InstrumentoNoEncontradoException.class)
    public ResponseEntity<String> handleInstrumentoNoEncontradoException(InstrumentoNoEncontradoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InversorExistenteException.class)
    public ResponseEntity<String> handleInversorExistenteException(InversorExistenteException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InversorNoEncontradoException.class)
    public ResponseEntity<String> handleInversorNoEncontradoException(InversorNoEncontradoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String>handleNullPointerException(NullPointerException ex){
        return new ResponseEntity<>("La cadena no puede ser nula", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
