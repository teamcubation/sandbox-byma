package springbootMigracion.java.com.example.demo.service;

import org.springframework.stereotype.Service;
import springbootMigracion.java.com.example.demo.dto.InstrumentoDTO;
import springbootMigracion.java.com.example.demo.model.Accion;
import springbootMigracion.java.com.example.demo.model.Bono;
import springbootMigracion.java.com.example.demo.model.InstrumentoFinanciero;
import springbootMigracion.java.com.example.demo.utils.validations.ErrorMessagesInstrumento;
import springbootMigracion.java.com.example.demo.utils.validations.ValidatorInstrumentoFinanciero;

@Service
public class InstrumentoFinancieroFactoryService {
    private static final String ACCION = "Accion";
    private static final String BONO = "Bono";
    public InstrumentoFinanciero crearInstrumento(InstrumentoDTO instrumentoDTO) throws Exception {
        ValidatorInstrumentoFinanciero.validarInstrumento(instrumentoDTO);
        return switch (instrumentoDTO.getTipo()) {
            case ACCION -> new Accion(instrumentoDTO.getNombre(), instrumentoDTO.getPrecio());
            case BONO ->
                    new Bono(instrumentoDTO.getNombre(), instrumentoDTO.getPrecio(), instrumentoDTO.getTasaDeInteres());
            default -> throw new IllegalArgumentException(ErrorMessagesInstrumento.ERROR_TIPO_INSTRUMENTO_NO_VALIDO);
        };
    }
}
