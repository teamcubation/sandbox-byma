package springbootMigracion.java.com.example.demo.utils.validations;

import springbootMigracion.java.com.example.demo.dto.InstrumentoDTO;
import springbootMigracion.java.com.example.demo.exception.ValidationException;

public class ValidatorInstrumentoFinanciero {

    public static void validarInstrumento(InstrumentoDTO instrumentoDTO) throws ValidationException {
        if (instrumentoDTO == null) {
            throw new ValidationException(ErrorMessages.INSTRUMENTO_NO_NULO);
        }
        if (instrumentoDTO.getNombre() == null || instrumentoDTO.getNombre().isEmpty()) {
            throw new ValidationException(ErrorMessages.NOMBRE_INSTRUMENTO_NO_NULO);
        }
        if (instrumentoDTO.getPrecio() == null) {
            throw new ValidationException(ErrorMessages.PRECIO_INSTRUMENTO_NO_NULO);
        }
        if (instrumentoDTO.getPrecio() <= 0) {
            throw new ValidationException(ErrorMessages.PRECIO_INSTRUMENTO_VALIDO);
        }

        if (instrumentoDTO.getTipo() == null || instrumentoDTO.getTipo().isEmpty()) {
            throw new ValidationException(ErrorMessages.TIPO_INSTRUMENTO_NO_NULO);
        }
        if ("bono".equalsIgnoreCase(instrumentoDTO.getTipo()) && instrumentoDTO.getTasaDeInteres() == null) {
            throw new ValidationException(ErrorMessages.TASA_INTERES_BONO_NO_NULO);
        }
    }
}
