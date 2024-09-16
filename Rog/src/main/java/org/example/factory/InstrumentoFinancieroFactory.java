package org.example.factory;

import org.example.model.Accion;
import org.example.model.Bono;
import org.example.model.InstrumentoFinanciero;

public class InstrumentoFinancieroFactory {
    public static InstrumentoFinanciero crearInstrumento(String tipo, String nombre, double precio, Double tasaDeInteres) {
        if ("Accion".equalsIgnoreCase(tipo)) {
            return new Accion(nombre, precio);
        } else if ("Bono".equalsIgnoreCase(tipo)) {
            return new Bono(nombre, precio, tasaDeInteres);
        }
        throw new IllegalArgumentException("Tipo de instrumento no valido.");
    }
}
