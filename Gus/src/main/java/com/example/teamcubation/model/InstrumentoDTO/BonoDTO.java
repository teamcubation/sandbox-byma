package com.example.teamcubation.model.InstrumentoDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BonoDTO {
    private String nombre;
    private Double precio;
    private Double tasaInteres;
}
