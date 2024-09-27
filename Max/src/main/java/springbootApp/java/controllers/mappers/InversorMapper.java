package springbootApp.java.controllers.mappers;

import springbootApp.java.DTOs.InversorDTO;
import springbootApp.java.models.Inversor;

public class InversorMapper {
    public static Inversor inversorDTOToInversor(InversorDTO inversorDTO) {
        return new Inversor(inversorDTO.getNombre(), inversorDTO.getDni());
    }

    public static InversorDTO inversorToInversorDTO(Inversor inversor) {
        return new InversorDTO(inversor.getNombre(), inversor.getDni());
    }
}
