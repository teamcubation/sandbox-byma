package axi.modelos;

import java.util.ArrayList;

public class Inversor implements Observer {
    private String nombre;
    private String dni;
    private ArrayList<InstrumentoFinanciero> cartera;

    public Inversor(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
        cartera = new ArrayList<>();
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("Error. El nombre no puede ser nulo o vacío.");
        }
        this.nombre = nombre;
    }

    public void setDni(String dni) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("Error. El dni no puede ser nulo o vacío.");
        }
        this.dni = dni;
    }

    public void suscribirse(InstrumentoFinanciero i) {
        cartera.add(i);
    }

    public void desuscribirse(InstrumentoFinanciero i) {
        cartera.remove(i);
    }

    @Override
    public void actualizar(double precio, String name) {

        System.out.println("el precio del instrumento " + name + "cambio a " + precio);
    }

    public String getDni() {
        return dni;
    }
}