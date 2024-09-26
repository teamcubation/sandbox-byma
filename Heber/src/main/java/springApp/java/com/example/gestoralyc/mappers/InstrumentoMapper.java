package springApp.java.com.example.gestoralyc.mappers;

import springApp.java.com.example.gestoralyc.dto.InstrumentoDTO;
import springApp.java.com.example.gestoralyc.exceptions.InvalidInstrumentoDataException;
import springApp.java.com.example.gestoralyc.models.AccionModel;
import springApp.java.com.example.gestoralyc.models.BonoModel;
import springApp.java.com.example.gestoralyc.models.InstrumentoFinancieroModel;
import springApp.java.com.example.gestoralyc.models.TipoInstrumento;

public class InstrumentoMapper {

    // Paso de dto a modelo
    public static InstrumentoFinancieroModel mapToModel(InstrumentoDTO dto) {
       if(dto.getFinDelParking() != null || dto.getFechaCreacion() != null || dto.getId() != null) {
            throw new InvalidInstrumentoDataException("Los campos 'finDelParking' y 'fechaCreacion' no son válidos para la creación de un instrumento.");
        }

        switch (dto.getTipo()) {
            case ACCION:
                if (dto.getTasaInteres() != null) {
                    throw new InvalidInstrumentoDataException("El campo 'tasaInteres' no es válido para una acción.");
                }
                if (dto.getDividendo() == null) {
                    throw new InvalidInstrumentoDataException("El campo 'dividendo' es requerido para una acción.");
                }
                return new AccionModel(dto.getTipo(), dto.getNombre(), dto.getPrecio(), dto.getDividendo());
            case BONO:
                if (dto.getDividendo() != null) {
                    throw new InvalidInstrumentoDataException("El campo 'dividendo' no es válido para un bono.");
                }
                if (dto.getTasaInteres() == null) {
                    throw new InvalidInstrumentoDataException("El campo 'tasaInteres' es requerido para un bono.");
                }
                return new BonoModel(dto.getTipo(), dto.getNombre(), dto.getPrecio(), dto.getTasaInteres());
            default:
                throw new IllegalArgumentException("Tipo de instrumento no válido");
        }
    }

    // Paso de modelo a dto
    public static InstrumentoDTO mapToDTO(InstrumentoFinancieroModel model) {
        InstrumentoDTO dto = new InstrumentoDTO();
        TipoInstrumento tipo = model.getTipo();
        dto.setTipo(tipo);
        dto.setNombre(model.getNombre());
        dto.setPrecio(model.getPrecio());

        if (tipo == TipoInstrumento.ACCION) {
            dto.setDividendo(((AccionModel) model).getDividendo());
        } else if (tipo == TipoInstrumento.BONO) {
            dto.setTasaInteres(((BonoModel) model).getTasaInteres());
        }

        dto.setId(model.getId());
        dto.calcularFinDelParking();

        return dto;
    }
}