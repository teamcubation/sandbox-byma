package springbootApp.java.models;




import springbootApp.java.DTOs.InstrumentoDTO;
import springbootApp.java.exceptions.InstrumentoDuplicadoException;
import springbootApp.java.exceptions.InstrumentoNoEncontradoException;
import springbootApp.java.exceptions.InversorExistenteException;
import springbootApp.java.services.ObserverService;

import java.util.ArrayList;
import java.util.List;

public abstract class InstrumentoFinanciero {

    private String nombre;
    private double precio;
    private Tipo tipo;
    private List <Inversor> inversoresList;

    public InstrumentoFinanciero(String nombre, double precio, Tipo tipo) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.inversoresList = new ArrayList<>();
    }

    public double getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    public Tipo getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "InstrumentoFinanciero{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", tipo=" + tipo + "\n inversoresList=" + inversoresList +
                '}';
    }

    public List<Inversor> getInversoresList() {
        return inversoresList;
    }

    public void setNombre(String nombre) {
        if (nombre == null) {
            throw new IllegalArgumentException("El nombre no puede ser nulo .");
        }
        if (nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede ser  vacío.");
        }
        this.nombre = nombre;
    }

    public void setTipo(Tipo tipo) {
        if (tipo == null) {
            throw new NullPointerException("El tipo no puede ser nulo .");
        }
        if (!tipo.equals(Tipo.ACCION) && !tipo.equals(Tipo.BONO)) {
            throw new IllegalArgumentException("Error. Tipo invalido");
        }
        this.tipo = tipo;
    }


    public void setPrecio(double precio) {
        try {
            if (precio > 0) {
                this.precio = precio;
                notificar();
            } else {
                throw new IllegalArgumentException("El precio no puede ser menor o igual a 0.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El precio debe ser un valor numérico.");
        }
    }

    public void actualizarInstrumento(InstrumentoDTO instrumento) {
        this.setNombre(instrumento.getNombre());
        this.setTipo(instrumento.getTipo());
        if (instrumento.getPrecio() != this.getPrecio()) {
            this.setPrecio(instrumento.getPrecio());
        }
    }


    public void notificar() {
        ObserverService.notificarCambioDePrecio(inversoresList, this);
    }

    public void suscribirse(Inversor inversor) throws InversorExistenteException, InstrumentoDuplicadoException, InstrumentoDuplicadoException {
        if (tieneInversor(inversor)) {
            throw new InversorExistenteException("Error. El inversor ya estaba suscripto");
        } else {
            this.inversoresList.add(inversor);
            inversor.suscribirse(this);
        }
    }

    public void desuscribirse(Inversor inversor) throws InstrumentoNoEncontradoException, InstrumentoNoEncontradoException {
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
