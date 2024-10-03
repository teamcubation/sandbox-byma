package com.example.project.controller.dto;

import com.example.project.exceptions.NoExisteEseTipoDeInstrumentoException;
import com.example.project.model.instrumentofinanciero.TipoInstrumentoFinanciero;
import lombok.Data;

import java.time.LocalDate;

@Data
public class InstrumentoFinancieroDTO {
    private String nombre;
    private Double precio;
    private LocalDate fechaDeEmision;
    private String stringTipoInstrumentoFinanciero;

    public TipoInstrumentoFinanciero getTipoInstrumentoFinanciero() throws NoExisteEseTipoDeInstrumentoException {
        switch (stringTipoInstrumentoFinanciero.toLowerCase()) {
            case "accion":
                return TipoInstrumentoFinanciero.ACCION;
            case "bono":
                return TipoInstrumentoFinanciero.BONO;
            default:
                throw new NoExisteEseTipoDeInstrumentoException("El tipo ingresado no corresponde a un tipo de instrumento conocido.");
        }
    }
}
