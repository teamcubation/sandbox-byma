package com.example.project.model.instrumentoFinanciero.factoryInstrumentos;

import com.example.project.model.instrumentoFinanciero.Bono;
import com.example.project.model.instrumentoFinanciero.InstrumentoFinanciero;
import com.example.project.model.instrumentoFinanciero.TipoInstrumentoFinanciero;

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
