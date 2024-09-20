package consoleApp.repository.factory;

import consoleApp.repository.factory.enums.TiposInstrumentosFinancieros;
import consoleApp.modelo.Accion;
import consoleApp.modelo.Bono;
import consoleApp.modelo.InstrumentoFinanciero;

public class InstrumentoFinancieroFactory  {
    public static InstrumentoFinanciero crearInstrumentoFinanciero(int tipo) {

        TiposInstrumentosFinancieros tipoInstrumentoFinanciero = TiposInstrumentosFinancieros.opcionSeleccionada(tipo);

        return switch (tipoInstrumentoFinanciero) {
            case BONO -> new Bono();
            case ACCION -> new Accion();
        };
    }
}
