package org.example.ejercicioGestionAccionesYBonos.modelo;

import org.example.ejercicioGestionAccionesYBonos.modelo.enumsModel.TipoInstrumentoFinanciero;

public class InstrumentoFactory {
    public static InstrumentoFinanciero nuevoInstrumento(String nombre, double precio, TipoInstrumentoFinanciero tipo) {

        InstrumentoFinanciero nuevoInstrumentoFinanciero = null;
        if (tipo.equals(TipoInstrumentoFinanciero.ACCION)) {
            nuevoInstrumentoFinanciero = new Accion(nombre, precio);
        }


        if (tipo.equals(TipoInstrumentoFinanciero.BONO)) {
            nuevoInstrumentoFinanciero = new Bono(nombre, precio);
        }
        return nuevoInstrumentoFinanciero;
    }
}
