package main.java.model.instrumentoFinanciero.factoryInstrumentos;

import main.java.model.instrumentoFinanciero.Accion;
import main.java.model.instrumentoFinanciero.InstrumentoFinanciero;

import java.time.LocalDate;

public class AccionFactory implements InstrumentoFinancieroFactory {

    @Override
    public InstrumentoFinanciero createInstrumentoFinanciero(String nombre, Double precio, LocalDate fechaDeEmision) {
        Accion accion = new Accion();
        try {
            accion.setNombre(nombre);
            accion.setPrecio(precio);
            accion.setFechaDeEmision(fechaDeEmision);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return accion;
    }
}
