package com.example.proyectoSpringBoot.dto;

import com.example.proyectoSpringBoot.excepciones.customExcepcions.OpcionInvalidaException;
import com.example.proyectoSpringBoot.model.InstrumentoFinanciero;
import com.example.proyectoSpringBoot.service.factory.InstrumentoFinancieroFactory;

public class InstrumentoFinancieroMapper {

    public static InstrumentoFinanciero toModel(InstrumentoFinancieroDTO dto) throws OpcionInvalidaException {
        InstrumentoFinanciero instrumentoFinancieroModel = InstrumentoFinancieroFactory.crearInstrumentoFinanciero(dto.getTipo());
        instrumentoFinancieroModel.setNombre(dto.getNombre());
        instrumentoFinancieroModel.setPrecio(dto.getPrecio());
        instrumentoFinancieroModel.setTipo(dto.getTipo());
        return instrumentoFinancieroModel;
    }

    public static InstrumentoFinancieroDTO toDTO(InstrumentoFinanciero instrumentoFinancieroModel) {
        InstrumentoFinancieroDTO dto = new InstrumentoFinancieroDTO();
        dto.setNombre(instrumentoFinancieroModel.getNombre());
        dto.setPrecio(instrumentoFinancieroModel.getPrecio());
        dto.setTipo(instrumentoFinancieroModel.getTipo());
        return dto;
    }
}
