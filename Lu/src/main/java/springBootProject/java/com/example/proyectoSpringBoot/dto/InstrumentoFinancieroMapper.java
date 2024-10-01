package com.example.proyectoSpringBoot.dto;

import com.example.proyectoSpringBoot.excepciones.customExcepcions.OpcionInvalidaException;
import com.example.proyectoSpringBoot.model.Accion;
import com.example.proyectoSpringBoot.model.Bono;
import com.example.proyectoSpringBoot.model.InstrumentoFinanciero;
import com.example.proyectoSpringBoot.model.factory.enums.TiposInstrumentosFinancieros;

public class InstrumentoFinancieroMapper {

    public static InstrumentoFinanciero toModel(InstrumentoFinancieroDTO dto) throws OpcionInvalidaException {
        TiposInstrumentosFinancieros tipoInstrumentoFinanciero = TiposInstrumentosFinancieros.opcionSeleccionada(dto.getTipo());

        return switch (tipoInstrumentoFinanciero) {
            case BONO -> Bono.builder()
                    .nombre(dto.getNombre())
                    .precio(dto.getPrecio())
                    .tipo(dto.getTipo())
                    .build();
            case ACCION -> Accion.builder()
                    .nombre(dto.getNombre())
                    .precio(dto.getPrecio())
                    .tipo(dto.getTipo())
                    .build();
        };
    }

    public static InstrumentoFinancieroDTO toDTO(InstrumentoFinanciero instrumentoFinancieroModel) {
        return InstrumentoFinancieroDTO.builder()
                .nombre(instrumentoFinancieroModel.getNombre())
                .precio(instrumentoFinancieroModel.getPrecio())
                .tipo(instrumentoFinancieroModel.getTipo())
                .build();
    }
}
