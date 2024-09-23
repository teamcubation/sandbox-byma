package consoleApp.models;

import java.util.ArrayList;

public abstract class InstrumentoFinanciero implements Observable {
    private static final String MSG_ERROR_NULO = "%s nulo o vacío";
    private static final String MSG_ERROR_PRECIO = "Precio debe ser mayor o igual a cero";

    private String nombre;
    private double precio;
    private ArrayList<Observer> observadores;

    public InstrumentoFinanciero(String nombre, double precio) throws IllegalArgumentException {
        setNombre(nombre);
        setPrecio(precio);
        observadores = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) throws IllegalArgumentException {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException(String.format(MSG_ERROR_NULO, "Nombre"));
        }
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    private void setPrecio(double precio) throws IllegalArgumentException {
        if (precio < 0) {
            throw new IllegalArgumentException(MSG_ERROR_PRECIO);
        }
        this.precio = precio;
    }

    public void modificarNombre(String nombre) {
        setNombre(nombre);
    }

    public void modificarPrecio(double nuevoPrecio) {
        if (this.precio != nuevoPrecio) {  // Solo notificar si el precio es diferente
            setPrecio(nuevoPrecio);
            notificarObservadores();  // Notificar a los inversores sobre el cambio de precio
        }
    }

    // Métodos del patrón Observer
    @Override
    public void agregarObservador(Observer observador) {
        observadores.add(observador);
    }

    @Override
    public void eliminarObservador(Observer observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservadores() {
        for (Observer observador : observadores) {
            observador.actualizar(this);  // Pasamos el instrumento actual para que el observador sepa qué cambió
        }
    }
}
