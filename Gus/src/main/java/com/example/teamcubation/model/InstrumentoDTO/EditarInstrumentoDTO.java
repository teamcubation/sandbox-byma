package com.example.teamcubation.model.InstrumentoDTO;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditarInstrumentoDTO {

    private String nombreInstrumento;
    private double nuevoPrecio;
    private String nuevoNombre;
    private String tipo;
}
