package  springbootApp.app.controllers.mappers;

import springbootApp.app.controllers.DTOs.InversorDTO;
import  springbootApp.app.models.Inversor;

public class InversorMapper {
    public static Inversor inversorDTOToInversor(InversorDTO inversorDTO) {
        return Inversor.builder()
                .nombre(inversorDTO.getNombre())
                .dni(inversorDTO.getDni())
                .build();
    }

    public static InversorDTO inversorToInversorDTO(Inversor inversor) {
        return InversorDTO.builder()
                .nombre(inversor.getNombre())
                .dni(inversor.getDni())
                .build();
    }
}
