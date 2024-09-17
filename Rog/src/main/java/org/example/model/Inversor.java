package org.example.model;

import org.example.observer.IObserver;

public class Inversor implements IObserver {
    private String nombre;

    public Inversor(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void actualizar(String nombreInstrumento, Double nuevoPrecio) {
        System.out.println("Inversor " + nombre + " ha sido notificado: El instrumento "
                + nombreInstrumento + " cambio de precio a: " + nuevoPrecio);
    }
}

