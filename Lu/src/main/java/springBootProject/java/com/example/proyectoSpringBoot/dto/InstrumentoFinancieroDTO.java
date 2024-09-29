package com.example.proyectoSpringBoot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstrumentoFinancieroDTO {
    private String nombre;
    private Double precio;
    private Integer tipo;
}
