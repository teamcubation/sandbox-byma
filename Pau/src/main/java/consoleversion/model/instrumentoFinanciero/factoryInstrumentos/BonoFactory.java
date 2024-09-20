package consoleversion.model.instrumentoFinanciero.factoryInstrumentos;

import consoleversion.model.instrumentoFinanciero.Bono;
import consoleversion.model.instrumentoFinanciero.InstrumentoFinanciero;
import consoleversion.model.instrumentoFinanciero.TipoInstrumentoFinanciero;

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
