package com.example.project.controller.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EditarInstrumentoDTO {
    private String nuevoNombre;
    private Double nuevoPrecio;
    private LocalDate nuevaFechaDeEmision;
}
