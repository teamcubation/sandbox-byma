package springbootproject.java.com.example.project.model.instrumentoFinanciero.factoryInstrumentos;

import springbootproject.java.com.example.project.model.instrumentoFinanciero.Accion;
import springbootproject.java.com.example.project.model.instrumentoFinanciero.InstrumentoFinanciero;
import springbootproject.java.com.example.project.model.instrumentoFinanciero.TipoInstrumentoFinanciero;

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
