package main.java.model.instrumentoFinanciero.factoryInstrumentos;

import main.java.model.instrumentoFinanciero.Bono;
import main.java.model.instrumentoFinanciero.InstrumentoFinanciero;

import java.time.LocalDate;

public class BonoFactory implements InstrumentoFinancieroFactory {

    @Override
    public InstrumentoFinanciero createInstrumentoFinanciero(String nombre, Double precio, LocalDate fechaDeEmision) {
        Bono bono = new Bono();
        try {
            bono.setNombre(nombre);
            bono.setPrecio(precio);
            bono.setFechaDeEmision(fechaDeEmision);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return bono;
    }
}
