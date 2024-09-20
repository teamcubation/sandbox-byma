package repository.factory;

import repository.factory.enums.TiposInstrumentosFinancieros;
import modelo.Accion;
import modelo.Bono;
import modelo.InstrumentoFinanciero;

public class InstrumentoFinancieroFactory  {
    public static InstrumentoFinanciero crearInstrumentoFinanciero(int tipo) {

        TiposInstrumentosFinancieros tipoInstrumentoFinanciero = TiposInstrumentosFinancieros.opcionSeleccionada(tipo);

        return switch (tipoInstrumentoFinanciero) {
            case BONO -> new Bono();
            case ACCION -> new Accion();
        };
    }
}
