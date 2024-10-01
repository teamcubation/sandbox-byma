package  springbootApp.app.controllers.mappers;

import springbootApp.app.controllers.DTOs.InversorDTO;
import  springbootApp.app.models.Inversor;

public class InversorMapper {
    public static Inversor inversorDTOToInversor(InversorDTO inversorDTO) {
        return new Inversor(inversorDTO.getNombre(), inversorDTO.getDni());
    }

    public static InversorDTO inversorToInversorDTO(Inversor inversor) {
        return new InversorDTO(inversor.getNombre(), inversor.getDni());
    }
}
