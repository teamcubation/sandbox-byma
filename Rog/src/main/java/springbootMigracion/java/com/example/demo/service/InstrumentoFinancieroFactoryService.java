package springbootMigracion.java.com.example.demo.service;

import org.springframework.stereotype.Service;
import springbootMigracion.java.com.example.demo.dto.InstrumentoDTO;
import springbootMigracion.java.com.example.demo.model.Accion;
import springbootMigracion.java.com.example.demo.model.Bono;
import springbootMigracion.java.com.example.demo.model.InstrumentoFinanciero;

@Service
public class InstrumentoFinancieroFactoryService {
    public InstrumentoFinanciero crearInstrumento(InstrumentoDTO instrumentoDTO) {
        if (instrumentoDTO == null) {
            throw new IllegalArgumentException("El InstrumentoDTO no puede ser nulo.");
        }
        if ("accion".equalsIgnoreCase(instrumentoDTO.getTipo())) {
            return new Accion(instrumentoDTO.getNombre(), instrumentoDTO.getPrecio());
        } else if ("bono".equalsIgnoreCase(instrumentoDTO.getTipo())) {
            return new Bono(instrumentoDTO.getNombre(), instrumentoDTO.getPrecio(), instrumentoDTO.getTasaDeInteres());
        }
        throw new IllegalArgumentException("Tipo de instrumento no valido.");
    }
}
