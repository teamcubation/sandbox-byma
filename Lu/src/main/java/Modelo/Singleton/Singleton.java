package Modelo.Singleton;

import Modelo.InstrumentoFinanciero;

import java.util.ArrayList;
import java.util.List;

public class Singleton {
    private static List<InstrumentoFinanciero> instrumentosFinancieros = null;

    private Singleton() {
    }

    public static List<InstrumentoFinanciero> getInstancia() {
        if (instrumentosFinancieros == null) {
            instrumentosFinancieros = new ArrayList<>();
        }
        return instrumentosFinancieros;
    }
}
