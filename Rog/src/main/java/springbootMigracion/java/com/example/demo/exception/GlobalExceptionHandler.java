package springbootMigracion.java.com.example.demo.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import springbootMigracion.java.com.example.demo.utils.logs.LogMessages;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({InstrumentoNoEncontradoException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessage instrumentoNoEncontradoException(HttpServletRequest req, Exception e) {
        log.error(LogMessages.INSTRUMENTO_NO_ENCONTRADO_URI.getMessage(), e.getMessage(), req.getRequestURI());
        return new ErrorMessage(e, req.getRequestURI());
    }

    @ExceptionHandler({InstrumentoDuplicadoException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorMessage instrumentoDuplicadoException(HttpServletRequest req, Exception e) {
        log.error(LogMessages.INSTRUMENTO_DUPLICADO_URI.getMessage(), e.getMessage(), req.getRequestURI());
        return new ErrorMessage(e, req.getRequestURI());
    }

    @ExceptionHandler({InversorNoEncontradoException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessage inversorNoEncontradoException(HttpServletRequest req, Exception e) {
        log.error(LogMessages.INVERSOR_NO_ENCONTRADO_URI.getMessage(), e.getMessage(), req.getRequestURI());
        return new ErrorMessage(e, req.getRequestURI());
    }

    @ExceptionHandler({InversorDuplicadoException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorMessage inversorDuplicadoException(HttpServletRequest req, Exception e) {
        log.error(LogMessages.INVERSOR_DUPLICADO_URI.getMessage(), e.getMessage(), req.getRequestURI());
        return new ErrorMessage(e, req.getRequestURI());
    }

    @ExceptionHandler({ValidationException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorMessage validationException(HttpServletRequest req, Exception e) {
        log.error(LogMessages.ERROR_VALIDACION_URI.getMessage(), e.getMessage(), req.getRequestURI());
        return new ErrorMessage(e, req.getRequestURI());
    }
}
