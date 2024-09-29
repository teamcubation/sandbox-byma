package springApp.java.com.example.gestoralyc.mappers;

import springApp.java.com.example.gestoralyc.dto.InstrumentoDTO;
import springApp.java.com.example.gestoralyc.exceptions.InvalidInstrumentoDataException;
import springApp.java.com.example.gestoralyc.models.AccionModel;
import springApp.java.com.example.gestoralyc.models.BonoModel;
import springApp.java.com.example.gestoralyc.models.InstrumentoFinancieroModel;
import springApp.java.com.example.gestoralyc.models.TipoInstrumento;

public class InstrumentoMapper {

    // Mapea de DTO a Model
    public static InstrumentoFinancieroModel mapToModel(InstrumentoDTO dto) throws InvalidInstrumentoDataException {
        validarCamposProhibidos(dto);
        dto.setFechaCreacion(java.time.LocalDate.now());

        InstrumentoFinancieroModel instrumento;

        switch (dto.getTipo()) {
            case ACCION:
                validarCamposAccion(dto);
                instrumento = AccionModel.builder()
                        .fechaCreacion(dto.getFechaCreacion())
                        .nombre(dto.getNombre())
                        .precio(dto.getPrecio())
                        .dividendo(dto.getDividendo())
                        .tipo(dto.getTipo())
                        .build();
                break;

            case BONO:
                validarCamposBono(dto);
                instrumento = BonoModel.builder()
                        .fechaCreacion(dto.getFechaCreacion())
                        .nombre(dto.getNombre())
                        .precio(dto.getPrecio())
                        .tasaInteres(dto.getTasaInteres())
                        .tipo(dto.getTipo())
                        .build();
                break;

            default:
                throw new InvalidInstrumentoDataException("Tipo de instrumento no válido.");
        }

        // Asignar el tipo de instrumento
        instrumento.setTipo(dto.getTipo());
        return instrumento;
    }

    // Validaciones para los campos prohibidos
    private static void validarCamposProhibidos(InstrumentoDTO dto) throws InvalidInstrumentoDataException {
        if (dto.getFinDelParking() != null || dto.getFechaCreacion() != null || dto.getId() != null) {
            throw new InvalidInstrumentoDataException("Los campos 'finDelParking', 'fechaCreacion' y 'id' no son válidos para la creación.");
        }
    }

    // Validaciones específicas para Accion
    private static void validarCamposAccion(InstrumentoDTO dto) throws InvalidInstrumentoDataException {
        if (dto.getDividendo() == null) {
            throw new InvalidInstrumentoDataException("El campo 'dividendo' es requerido para una acción.");
        }
        if (dto.getTasaInteres() != null) {
            throw new InvalidInstrumentoDataException("El campo 'tasaInteres' no debe enviarse para una acción.");
        }
    }

    // Validaciones específicas para Bono
    private static void validarCamposBono(InstrumentoDTO dto) throws InvalidInstrumentoDataException {
        if (dto.getTasaInteres() == null) {
            throw new InvalidInstrumentoDataException("El campo 'tasaInteres' es requerido para un bono.");
        }
        if (dto.getDividendo() != null) {
            throw new InvalidInstrumentoDataException("El campo 'dividendo' no debe enviarse para un bono.");
        }
    }

    // Mapea de Model a DTO
    public static InstrumentoDTO mapToDTO(InstrumentoFinancieroModel model) {
        InstrumentoDTO dto = new InstrumentoDTO();
        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        dto.setPrecio(model.getPrecio());
        dto.setTipo(model.getTipo());

        if (model.getTipo() == TipoInstrumento.ACCION) {
            dto.setDividendo(((AccionModel) model).getDividendo());
        } else if (model.getTipo() == TipoInstrumento.BONO) {
            dto.setTasaInteres(((BonoModel) model).getTasaInteres());
        }

        // Calcular el fin del parking
        dto.calcularFinDelParking();
        return dto;
    }
}
