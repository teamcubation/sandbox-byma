package springbootApp.java.models;
import springbootApp.java.exceptions.InstrumentoDuplicadoException;
import springbootApp.java.exceptions.InstrumentoNoEncontradoException;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

public class Inversor implements Observer{
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

    public void suscribirse(InstrumentoFinanciero instrumento) {
        if (tieneInstrumento(instrumento)) {
            throw new InstrumentoDuplicadoException("Error. Ya estas suscripto a este instrumento");
        }
        cartera.add(instrumento);
    }

    public void desuscribirse(InstrumentoFinanciero instrumento) {
        if (!tieneInstrumento(instrumento)) {
            throw new InstrumentoNoEncontradoException("Error. Instrumento no encontrado");
        }
        cartera.remove(instrumento);
    }

    private boolean tieneInstrumento(InstrumentoFinanciero instrumento) {
        if (instrumento == null)
            return false;
        else
            return cartera.stream().anyMatch(i -> i.equals(instrumento));
    }

    public String getDni() {
        return dni;
    }

    public void consultarInstrumentos() {
        for (InstrumentoFinanciero instrumentoFinanciero : cartera) {
            System.out.println(instrumentoFinanciero);
        }
    }

    @Override
    public String toString() {
        return "Inversor{" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                '}';
    }


    @Override
    public void actualizar(InstrumentoFinanciero instrumento) {
        System.out.println("el precio del instrumento " + instrumento.getNombre() + " cambio a "
                + instrumento.getPrecio());
    }
}
