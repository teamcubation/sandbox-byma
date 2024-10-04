package springApp.java.com.example.gestoralyc.mappers;

import springApp.java.com.example.gestoralyc.dto.AccionDTO;
import springApp.java.com.example.gestoralyc.exceptions.InvalidInstrumentoDataException;
import springApp.java.com.example.gestoralyc.models.AccionModel;
import springApp.java.com.example.gestoralyc.utils.ValidationUtils;

public class AccionMapper {

    public static AccionModel mapToModel(AccionDTO accionDTO) throws InvalidInstrumentoDataException {
        ValidationUtils.validarAccionDTO(accionDTO);
        return AccionModel.builder()
                .nombre(accionDTO.getNombre())
                .precio(accionDTO.getPrecio())
                .fechaCreacion(java.time.LocalDate.now())
                .dividendo(accionDTO.getDividendo())
                .build();
    }

    public static AccionDTO mapToDTO(AccionModel model) {
        AccionDTO dto = new AccionDTO();
        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        dto.setPrecio(model.getPrecio());
        dto.setDividendo(model.getDividendo());
        dto.calcularFinDelParking();

        return dto;
    }

}
