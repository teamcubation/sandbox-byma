package springApp.java.com.example.gestoralyc.mappers;

import springApp.java.com.example.gestoralyc.dto.AccionDTO;
import springApp.java.com.example.gestoralyc.dto.BonoDTO;
import springApp.java.com.example.gestoralyc.exceptions.InvalidInstrumentoDataException;
import springApp.java.com.example.gestoralyc.models.BonoModel;

public class BonoMapper {

    // Mapea de DTO a Model
    public static BonoModel mapToModel(BonoDTO dto) throws InvalidInstrumentoDataException {
        validarCamposProhibidos(dto);
        validarCamposBono(dto);

        return BonoModel.builder()
                .nombre(dto.getNombre())
                .precio(dto.getPrecio())
                .fechaCreacion(dto.getFechaCreacion() == null ? java.time.LocalDate.now() : dto.getFechaCreacion())
                .tasaInteres(dto.getTasaInteres())
                .build();
    }

    // Mapea de Model a DTO
    public static BonoDTO mapToDTO(BonoModel model) {
        BonoDTO dto = new BonoDTO();
        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        dto.setPrecio(model.getPrecio());
        dto.setTasaInteres(model.getTasaInteres());

        // Calcular el fin del parking, si es necesario
        dto.calcularFinDelParking();

        return dto;
    }

    private static void validarCamposProhibidos(BonoDTO dto) throws InvalidInstrumentoDataException {
        if (dto.getFinDelParking() != null || dto.getFechaCreacion() != null || dto.getId() != null) {
            throw new InvalidInstrumentoDataException("Los campos 'finDelParking', 'fechaCreacion' y 'id' no son válidos para la creación.");
        }
    }

    // Validaciones específicas para Bono
    private static void validarCamposBono(BonoDTO dto) throws InvalidInstrumentoDataException {
        if (dto.getTasaInteres() == null) {
            throw new InvalidInstrumentoDataException("El campo 'tasaInteres' es requerido para un bono.");
        }
        if(dto.getNombre() == null || dto.getNombre().isEmpty()) {
            throw new InvalidInstrumentoDataException("El campo 'nombre' es requerido para un bono.");
        }
        if(dto.getPrecio() == 0 || dto.getPrecio() == null) {
            throw new InvalidInstrumentoDataException("El campo 'precio' es requerido para un bono.");
        }
    }
}
