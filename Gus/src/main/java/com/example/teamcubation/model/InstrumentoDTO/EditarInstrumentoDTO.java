package com.example.teamcubation.model.InstrumentoDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditarInstrumentoDTO {


    //Atributos del DTO tienen que se OBJETOS
    private String nombreInstrumento;
    private Double nuevoPrecio;
    private String nuevoNombre;
    private String tipo;
}
