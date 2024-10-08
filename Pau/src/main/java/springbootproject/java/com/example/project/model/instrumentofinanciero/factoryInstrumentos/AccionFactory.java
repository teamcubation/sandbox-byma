package com.example.project.model.instrumentofinanciero.factoryInstrumentos;

import com.example.project.model.instrumentofinanciero.Accion;
import com.example.project.model.instrumentofinanciero.InstrumentoFinanciero;
import com.example.project.model.instrumentofinanciero.TipoInstrumentoFinanciero;

import java.time.LocalDate;

public class AccionFactory implements InstrumentoFinancieroFactory {

    @Override
    public InstrumentoFinanciero createInstrumentoFinanciero(String nombre, Double precio, LocalDate fechaDeEmision) {
        Accion accion = new Accion();
        accion.setNombre(nombre);
        accion.setPrecio(precio);
        accion.setFechaDeEmision(fechaDeEmision);
        accion.setTipoInstrumentoFinanciero(TipoInstrumentoFinanciero.ACCION);
        return accion;
    }
}
