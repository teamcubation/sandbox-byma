package axi.modelos;

import axi.excepciones.InstrumentoNoEncontradoException;
import axi.excepciones.InversorExistenteException;
import axi.servicios.InversorService;

import java.util.ArrayList;

public abstract class InstrumentoFinanciero {
    private String nombre;
    private double precio;
    private Tipo tipo;
    private ArrayList<Inversor> inversores;

    public InstrumentoFinanciero(String nombre, double precio, Tipo tipo) {
        this.setNombre(nombre);
        this.precio = precio;
        this.setTipo(tipo);
        this.inversores = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public Tipo getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "InstrumentoFinanciero{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", tipo=" + tipo +
                ", inversores=" + inversores +
                '}';
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }
        this.nombre = nombre;
    }

    public void setTipo(Tipo tipo) {
        if (!tipo.equals(Tipo.ACCION) && !tipo.equals(Tipo.BONO)) {
            throw new IllegalArgumentException("Error. Tipo invalido");
        }
        this.tipo = tipo;
    }

    public void setPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser menor a 0.");
        } else {
            this.precio = precio;
            this.notificar();
        }
    }

    public void notificar() {
        InversorService.notificarCambioDePrecio(inversores, this);
    }
    public void suscribirse(Inversor inversor){
        if (!tieneInversor(inversor)){
            this.inversores.add(inversor);
            inversor.suscribirse(this);
        } else {
            throw new InversorExistenteException("Error. El inversor ya estaba suscripto");
        }
    }
    public void desuscribirse(Inversor inversor){
        if (tieneInversor(inversor)){
            this.inversores.remove(inversor);
            inversor.desuscribirse(this);
        } else
            throw new InstrumentoNoEncontradoException("Error. inversor no encontrado");
    }
    private boolean tieneInversor(Inversor inversor){
        return inversores.stream().anyMatch(i -> i.equals(inversor));
    }
}
