package springbootMigracion.java.com.example.demo.factory;

import springbootMigracion.java.com.example.demo.dto.InstrumentoDTO;
import springbootMigracion.java.com.example.demo.model.Accion;
import springbootMigracion.java.com.example.demo.model.Bono;
import springbootMigracion.java.com.example.demo.model.InstrumentoFinanciero;

public class InstrumentoFinancieroFactory {
    public static InstrumentoFinanciero crearInstrumento(InstrumentoDTO instrumentoDTO) {
        if ("accion".equalsIgnoreCase(instrumentoDTO.getTipo())) {
            return new Accion(instrumentoDTO.getNombre(), instrumentoDTO.getPrecio());
        } else if ("bono".equalsIgnoreCase(instrumentoDTO.getTipo())) {
            return new Bono(instrumentoDTO.getNombre(), instrumentoDTO.getPrecio(), instrumentoDTO.getTasaDeInteres());
        }
        throw new IllegalArgumentException("Tipo de instrumento no valido.");
    }
}
