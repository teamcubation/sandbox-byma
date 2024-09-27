package springApp.java.com.example.gestoralyc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    private static final String MSJ_ERROR_INSTRUMENTO_NO_VALIDO = "Tipo de instrumento no válido";

    // Manejo de IllegalArgumentException y InvalidTypeIdException
    @ExceptionHandler({IllegalArgumentException.class, com.fasterxml.jackson.databind.exc.InvalidTypeIdException.class})
    public ResponseEntity<String> handleInvalidArgumentException(Exception ex) {
        return new ResponseEntity<>(MSJ_ERROR_INSTRUMENTO_NO_VALIDO, HttpStatus.BAD_REQUEST);
    }

    // Manejo de InstrumentoDuplicadoException, InstrumentoNoEncontradoException e InvalidInstrumentoDataException
    @ExceptionHandler({InstrumentoDuplicadoException.class, InstrumentoNoEncontradoException.class, InvalidInstrumentoDataException.class})
    public ResponseEntity<String> handleInstrumentoExceptions(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    // Manejo de excepciones generales
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

