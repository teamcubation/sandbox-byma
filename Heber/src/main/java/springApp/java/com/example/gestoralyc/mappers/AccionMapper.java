package springApp.java.com.example.gestoralyc.mappers;

import springApp.java.com.example.gestoralyc.dto.AccionDTO;
import springApp.java.com.example.gestoralyc.exceptions.InvalidInstrumentoDataException;
import springApp.java.com.example.gestoralyc.models.AccionModel;

public class AccionMapper {

    // Mapea de DTO a Model
    public static AccionModel mapToModel(AccionDTO dto) throws InvalidInstrumentoDataException {
        validarCamposProhibidos(dto);
        validarCamposAccion(dto);

        return AccionModel.builder()
                .nombre(dto.getNombre())
                .precio(dto.getPrecio())
                .fechaCreacion(dto.getFechaCreacion() == null ? java.time.LocalDate.now() : dto.getFechaCreacion())
                .dividendo(dto.getDividendo())
                .build();
    }

    // Mapea de Model a DTO
    public static AccionDTO mapToDTO(AccionModel model) {
        AccionDTO dto = new AccionDTO();
        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        dto.setPrecio(model.getPrecio());
        dto.setDividendo(model.getDividendo());

        // Calcular el fin del parking, si es necesario
        dto.calcularFinDelParking();

        return dto;
    }

    private static void validarCamposProhibidos(AccionDTO dto) throws InvalidInstrumentoDataException {
        if (dto.getFinDelParking() != null || dto.getFechaCreacion() != null || dto.getId() != null) {
            throw new InvalidInstrumentoDataException("Los campos 'finDelParking', 'fechaCreacion' y 'id' no son válidos para la creación.");
        }
    }

    // Validaciones específicas para Accion
    private static void validarCamposAccion(AccionDTO dto) throws InvalidInstrumentoDataException {
        if (dto.getDividendo() == null) {
            throw new InvalidInstrumentoDataException("El campo 'dividendo' es requerido para una acción.");
        }
        if (dto.getNombre() == null || dto.getNombre().isEmpty()) {
            throw new InvalidInstrumentoDataException("El campo 'nombre' es requerido para una acción.");
        }
        if (dto.getPrecio() == 0 || dto.getPrecio() == null) {
            throw new InvalidInstrumentoDataException("El campo 'precio' es requerido para una acción.");
        }

    }
}
