package com.example.teamcubation.util.mappers;

import com.example.teamcubation.model.Bono;
import com.example.teamcubation.model.InstrumentoDTO.BonoDTO;

public class BonoMapper {

    public static BonoDTO toBonoDTO(Bono bono) {
        return BonoDTO
                .builder()
                .nombre(bono.getNombre())
                .precio(bono.getPrecio())
                .tasaInteres(bono.getTasaInteres())
                .build();
    }

    public static Bono toBono(BonoDTO bonoDTO) {
        return Bono
                .builder()
                .nombre(bonoDTO.getNombre())
                .precio(bonoDTO.getPrecio())
                .tasaInteres(bonoDTO.getTasaInteres())
                .build();
    }
}
