package springApp.java.com.example.gestoralyc.mappers;

import springApp.java.com.example.gestoralyc.dto.InstrumentoDTO;
import springApp.java.com.example.gestoralyc.exceptions.InvalidInstrumentoDataException;
import springApp.java.com.example.gestoralyc.models.AccionModel;
import springApp.java.com.example.gestoralyc.models.BonoModel;
import springApp.java.com.example.gestoralyc.models.InstrumentoFinancieroModel;

public class InstrumentoMapper {

    public static InstrumentoFinancieroModel mapToModel(InstrumentoDTO dto) {
        switch (dto.getTipo()) {
            case ACCION:
                if (dto.getTasaInteres() != null) {
                    throw new InvalidInstrumentoDataException("El campo 'tasaInteres' no es válido para una acción.");
                }
                if (dto.getDividendo() == null) {
                    throw new InvalidInstrumentoDataException("El campo 'dividendo' es requerido para una acción.");
                }
                return new AccionModel(dto.getNombre(), dto.getPrecio(), dto.getDividendo());
            case BONO:
                if (dto.getDividendo() != null) {
                    throw new InvalidInstrumentoDataException("El campo 'dividendo' no es válido para un bono.");
                }
                if (dto.getTasaInteres() == null) {
                    throw new InvalidInstrumentoDataException("El campo 'tasaInteres' es requerido para un bono.");
                }
                return new BonoModel(dto.getNombre(), dto.getPrecio(), dto.getTasaInteres());
            default:
                throw new IllegalArgumentException("Tipo de instrumento no válido");
        }
    }
}
