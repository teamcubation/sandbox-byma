package com.example.teamcubation.model;

import com.example.teamcubation.model.instrumentoEnums.TipoInstrumentoFinanciero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public abstract class InstrumentoFinanciero {
    
    //El nombre es unico para cada instrumento, ya sea bono o accion
    private String nombre;
    private double precio;
    private TipoInstrumentoFinanciero tipo;


    public abstract String mostrarInstrumento();

}
