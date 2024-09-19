package org.example.model;

import org.example.service.NotificadorService;

public abstract class InstrumentoFinanciero extends NotificadorService {
    private String nombre;
    private double precio;
    private String tipo;

    public InstrumentoFinanciero(String nombre, double precio, String tipo) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
    }

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
        notificarObservadores(this.nombre, this.precio);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "InstrumentoFinanciero{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", tipo='" + tipo + '\'';
    }
}
