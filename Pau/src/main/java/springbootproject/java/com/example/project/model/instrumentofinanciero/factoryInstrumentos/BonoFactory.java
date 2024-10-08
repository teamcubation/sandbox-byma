package com.example.project.model.instrumentofinanciero.factoryInstrumentos;

import com.example.project.model.instrumentofinanciero.Bono;
import com.example.project.model.instrumentofinanciero.InstrumentoFinanciero;
import com.example.project.model.instrumentofinanciero.TipoInstrumentoFinanciero;

import java.time.LocalDate;

public class BonoFactory implements InstrumentoFinancieroFactory {

    @Override
    public InstrumentoFinanciero createInstrumentoFinanciero(String nombre, Double precio, LocalDate fechaDeEmision) {
        Bono bono = new Bono();
        bono.setNombre(nombre);
        bono.setPrecio(precio);
        bono.setFechaDeEmision(fechaDeEmision);
        bono.setTipoInstrumentoFinanciero(TipoInstrumentoFinanciero.BONO);
        return bono;
    }
}
