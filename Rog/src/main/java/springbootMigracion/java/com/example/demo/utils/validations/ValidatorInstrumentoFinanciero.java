package springbootMigracion.java.com.example.demo.utils.validations;

import springbootMigracion.java.com.example.demo.dto.InstrumentoDTO;
import springbootMigracion.java.com.example.demo.exception.ValidationException;

public class ValidatorInstrumentoFinanciero {
    private static final String TIPO_BONO = "bono";
    public static void validarInstrumento(InstrumentoDTO instrumentoDTO) throws ValidationException {
        String mensajeError ="";
        if (instrumentoDTO == null) {
            mensajeError += ErrorMessagesInstrumento.INSTRUMENTO_NO_NULO + "-";
        }
        if (instrumentoDTO.getNombre() == null || instrumentoDTO.getNombre().isEmpty()) {
            mensajeError += ErrorMessagesInstrumento.NOMBRE_INSTRUMENTO_NO_NULO + "-";
        }
        if (instrumentoDTO.getPrecio() == null) {
            mensajeError += ErrorMessagesInstrumento.PRECIO_INSTRUMENTO_NO_NULO + "-";
        } else if (instrumentoDTO.getPrecio() <= 0) {
            mensajeError += ErrorMessagesInstrumento.PRECIO_INSTRUMENTO_VALIDO + "-";
        }
        if (instrumentoDTO.getTipo() == null || instrumentoDTO.getTipo().isEmpty()) {
            mensajeError += ErrorMessagesInstrumento.TIPO_INSTRUMENTO_NO_NULO + "-";
        }
        if (TIPO_BONO.equalsIgnoreCase(instrumentoDTO.getTipo()) && instrumentoDTO.getTasaDeInteres() == null) {
            mensajeError += ErrorMessagesInstrumento.TASA_INTERES_BONO_NO_NULO + "-";
        }
        if (!mensajeError.isEmpty()){
            throw new ValidationException(mensajeError);
        }
    }
}
