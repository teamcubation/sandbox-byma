package com.example.teamcubation.model;


import com.example.teamcubation.model.instrumentoEnums.TipoInstrumentoFinanciero;

public class Bono extends InstrumentoFinanciero {

    public Bono(String nombre, double precio) {
        super(nombre, precio, TipoInstrumentoFinanciero.BONO);
    }

    @Override
    public String mostrarInstrumento() {
        return "Nombre: " + this.getNombre() + " || Precio: $" + this.getPrecio() + " || Tipo: " + this.getTipo();
    }
}
