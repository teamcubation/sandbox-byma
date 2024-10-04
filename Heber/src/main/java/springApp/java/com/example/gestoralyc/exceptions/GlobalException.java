package springApp.java.com.example.gestoralyc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    private static final String MSJ_ERROR_INSTRUMENTO_NO_VALIDO = "Tipo de instrumento no válido";

    @ExceptionHandler({IllegalArgumentException.class, com.fasterxml.jackson.databind.exc.InvalidTypeIdException.class})
    public ResponseEntity<String> handleInvalidArgumentException(Exception ex) {
        return new ResponseEntity<>(MSJ_ERROR_INSTRUMENTO_NO_VALIDO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>(MSJ_ERROR_INSTRUMENTO_NO_VALIDO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InstrumentoDuplicadoException.class, InstrumentoNoEncontradoException.class, InvalidInstrumentoDataException.class})
    public ResponseEntity<String> handleInstrumentoExceptions(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }


}
