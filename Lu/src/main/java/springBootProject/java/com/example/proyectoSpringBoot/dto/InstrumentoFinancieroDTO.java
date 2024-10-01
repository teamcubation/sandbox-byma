package com.example.proyectoSpringBoot.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InstrumentoFinancieroDTO {
    private String nombre;
    private Double precio;
    private Integer tipo;
}
