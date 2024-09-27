package springBootProject.java.com.example.proyectoSpringBoot.excepciones;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import springBootProject.java.com.example.proyectoSpringBoot.excepciones.customExcepcions.InstrumentoDuplicadoException;
import springBootProject.java.com.example.proyectoSpringBoot.excepciones.customExcepcions.InstrumentoNoEncontradoException;
import springBootProject.java.com.example.proyectoSpringBoot.excepciones.customExcepcions.OpcionInvalidaException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({InstrumentoDuplicadoException.class, OpcionInvalidaException.class})
    @ResponseBody
    public ExcepcionMessage conflictExcepcion(HttpServletRequest request, Exception duplicateExcepcion) {
        return new ExcepcionMessage(request.getRequestURI(), duplicateExcepcion);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({InstrumentoNoEncontradoException.class})
    @ResponseBody
    public ExcepcionMessage notFoundExcepcion(HttpServletRequest request, Exception duplicateExcepcion) {
        return new ExcepcionMessage(request.getRequestURI(), duplicateExcepcion);
    }
}
