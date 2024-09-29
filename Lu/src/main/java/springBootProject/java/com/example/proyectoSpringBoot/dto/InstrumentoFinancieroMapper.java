package com.example.proyectoSpringBoot.dto;

import com.example.proyectoSpringBoot.model.InstrumentoFinanciero;

public class InstrumentoFinancieroMapper {

    public static InstrumentoFinanciero toModel(InstrumentoFinancieroDTO dto) {
        InstrumentoFinanciero usuario = new InstrumentoFinanciero();
        usuario.setNombre(dto.getNombre());
        usuario.setPrecio(dto.getPrecio());
        usuario.setTipo(dto.getTipo());
        return usuario;
    }

    public static InstrumentoFinancieroDTO toDTO(InstrumentoFinanciero usuario) {
        InstrumentoFinancieroDTO dto = new InstrumentoFinancieroDTO();
        dto.setNombre(usuario.getNombre());
        dto.setPrecio(usuario.getPrecio());
        dto.setTipo(usuario.getTipo());
        return dto;
    }
}
