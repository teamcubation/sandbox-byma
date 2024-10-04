package springApp.java.com.example.gestoralyc.mappers;

import springApp.java.com.example.gestoralyc.dto.BonoDTO;
import springApp.java.com.example.gestoralyc.exceptions.InvalidInstrumentoDataException;
import springApp.java.com.example.gestoralyc.models.BonoModel;
import springApp.java.com.example.gestoralyc.utils.ValidationUtils;

public class BonoMapper {

    public static BonoModel mapToModel(BonoDTO dto) throws InvalidInstrumentoDataException {
        ValidationUtils.validarBonoDTO(dto);
        return BonoModel.builder()
                .nombre(dto.getNombre())
                .precio(dto.getPrecio())
                .fechaCreacion(dto.getFechaCreacion() == null ? java.time.LocalDate.now() : dto.getFechaCreacion())
                .tasaInteres(dto.getTasaInteres())
                .build();
    }

    public static BonoDTO mapToDTO(BonoModel model) {
        BonoDTO dto = new BonoDTO();
        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        dto.setPrecio(model.getPrecio());
        dto.setTasaInteres(model.getTasaInteres());
        dto.calcularFinDelParking();

        return dto;
    }

}
