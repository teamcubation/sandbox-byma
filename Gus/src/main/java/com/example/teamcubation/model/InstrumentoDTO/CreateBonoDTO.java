package com.example.teamcubation.model.InstrumentoDTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBonoDTO {
    private String nombre;
    private Double precio;
    private Double tasaInteres;
}
