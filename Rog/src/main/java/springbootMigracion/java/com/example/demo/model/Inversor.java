package springbootMigracion.java.com.example.demo.model;

import org.example.service.IObserver;

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

