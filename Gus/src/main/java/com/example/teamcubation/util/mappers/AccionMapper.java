package com.example.teamcubation.util.mappers;

import com.example.teamcubation.model.Accion;
import com.example.teamcubation.model.InstrumentoDTO.AccionDTO;

public class AccionMapper {

    public static Accion toAccion(AccionDTO accionDTO) {
        return Accion
                .builder()
                .nombre(accionDTO.getNombre())
                .precio(accionDTO.getPrecio())
                .dividendo(accionDTO.getDividendo())
                .build();
    }

    public static AccionDTO toAccionDTO(Accion accion) {
        return AccionDTO
                .builder()
                .nombre(accion.getNombre())
                .precio(accion.getPrecio())
                .dividendo(accion.getDividendo())
                .build();
    }
}
