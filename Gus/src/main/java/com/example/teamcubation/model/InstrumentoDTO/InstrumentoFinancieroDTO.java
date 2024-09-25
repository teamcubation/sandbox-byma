package com.example.teamcubation.model.InstrumentoDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InstrumentoFinancieroDTO {

    private String nombreInstrumento;
    private Double precio;
    private String tipo;
}
