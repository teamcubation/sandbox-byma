package com.example.project.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({InstrumentoDuplicadoException.class})
    @ResponseBody
    public ErrorMessage instrumentoDuplicadoHandler(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({InstrumentoNoEncontradoException.class})
    @ResponseBody
    public ErrorMessage instrumentoNoEncontradoHandler(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({InversorNoEncontradoException.class})
    @ResponseBody
    public ErrorMessage inversorNoEncontradoHandler(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({InversorYaRegistradoException.class})
    @ResponseBody
    public ErrorMessage inversorYaRegistradoHandler(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoExisteEseTipoDeInstrumentoException.class})
    @ResponseBody
    public ErrorMessage NoExisteEseTipoDeInstrumentoHandler(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }
}
