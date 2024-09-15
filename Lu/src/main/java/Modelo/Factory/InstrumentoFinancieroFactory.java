package Modelo.Factory;

import Enums.TiposInstrumentosFinancieros;
import Modelo.Accion;
import Modelo.Bono;
import Modelo.InstrumentoFinanciero;

public class InstrumentoFinancieroFactory  {
    public static InstrumentoFinanciero crearInstrumentoFinanciero(int tipo) {

        TiposInstrumentosFinancieros tipoInstrumentoFinanciero = TiposInstrumentosFinancieros.opcionSeleccionada(tipo);

        return switch (tipoInstrumentoFinanciero) {
            case BONO -> new Bono();
            case ACCION -> new Accion();
        };
    }
}
