package org.example.ejercicioGestionAccionesYBonos.modelo;

import org.example.ejercicioGestionAccionesYBonos.modelo.enumsModel.TipoInstrumentoFinanciero;

public class Bono extends InstrumentoFinanciero {
    public Bono(String nombre, double precio) {
        super(nombre, precio, TipoInstrumentoFinanciero.BONO);
    }

    @Override
    public String mostrarInstrumento() {
        return "Nombre: " + this.getNombre() + " || Precio: $" + this.getPrecio() + " || Tipo: " + this.getTipo();
    }
}
