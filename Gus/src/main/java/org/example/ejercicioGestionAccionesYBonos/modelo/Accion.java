package org.example.ejercicioGestionAccionesYBonos.modelo;

import org.example.ejercicioGestionAccionesYBonos.util.TipoInstrumentoFinanciero;

public class Accion extends InstrumentoFinanciero {

    public Accion(String nombre, double precio) {
        super(nombre, precio, TipoInstrumentoFinanciero.ACCION);
    }

    @Override
    public String mostrarInstrumento() {
        return  "Nombre: " + this.getNombre() + " || Precio: $" + this.getPrecio() + " || Tipo: " + this.getTipo();
    }
}
