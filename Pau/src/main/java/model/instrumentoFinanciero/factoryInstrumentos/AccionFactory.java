package model.instrumentoFinanciero.factoryInstrumentos;

import model.instrumentoFinanciero.Accion;
import model.instrumentoFinanciero.InstrumentoFinanciero;
import model.instrumentoFinanciero.TipoInstrumentoFinanciero;

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
