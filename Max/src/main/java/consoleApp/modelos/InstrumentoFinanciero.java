package consoleApp.modelos;

import consoleApp.excepciones.InstrumentoDuplicadoException;
import consoleApp.excepciones.InstrumentoNoEncontradoException;
import consoleApp.excepciones.InversorExistenteException;
import consoleApp.servicios.ObserverService;

import java.util.ArrayList;

public abstract class InstrumentoFinanciero {
    private String nombre;
    private double precio;
    private Tipo tipo;
    private ArrayList<Inversor> inversoresList;

    public InstrumentoFinanciero(String nombre, double precio, Tipo tipo) {
        this.setNombre(nombre);
        this.precio = precio;
        this.setTipo(tipo);
        this.inversoresList = new ArrayList<>();
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
                ", inversores=" + inversoresList +
                '}';
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
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
        try {
            if (precio > 0) {
                this.precio = precio;
                this.notificar();
            } else {
                throw new IllegalArgumentException("El precio no puede ser menor o igual a 0.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El precio debe ser un valor numérico.");
        }
    }


    public void notificar() {
        ObserverService.notificarCambioDePrecio(inversoresList, this);
    }

    public void suscribirse(Inversor inversor) throws InversorExistenteException, InstrumentoDuplicadoException {
        if (tieneInversor(inversor)) {
            throw new InversorExistenteException("Error. El inversor ya estaba suscripto");
        } else {
            this.inversoresList.add(inversor);
            inversor.suscribirse(this);
        }
    }

    public void desuscribirse(Inversor inversor) throws InstrumentoNoEncontradoException {
        if (!tieneInversor(inversor)) {
            throw new InstrumentoNoEncontradoException("Error. inversor no encontrado");
        }
        this.inversoresList.remove(inversor);
        inversor.desuscribirse(this);
    }

    private boolean tieneInversor(Inversor inversor) {
        if (inversor == null)
            return false;
        else
            return inversoresList.stream().anyMatch(i -> i.equals(inversor));
    }
}
