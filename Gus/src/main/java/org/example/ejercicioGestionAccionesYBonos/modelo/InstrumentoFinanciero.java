package org.example.ejercicioGestionAccionesYBonos.modelo;

import org.example.ejercicioGestionAccionesYBonos.util.TipoInstrumentoFinanciero;

public abstract class InstrumentoFinanciero {
    //El nombre es unico para cada instrumento, ya sea bono o accion
    private String nombre;
    private double precio;
    private TipoInstrumentoFinanciero tipo;

    public InstrumentoFinanciero(String nombre, double precio, TipoInstrumentoFinanciero tipo) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
    }

    public abstract String mostrarInstrumento();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public TipoInstrumentoFinanciero getTipo() {
        return tipo;
    }

    public void setTipo(TipoInstrumentoFinanciero tipo) {
        this.tipo = tipo;
    }


}
