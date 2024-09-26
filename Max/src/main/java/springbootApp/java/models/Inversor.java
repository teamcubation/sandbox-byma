package springbootApp.java.models;
import springbootApp.java.DTOs.InversorDTO;
import springbootApp.java.exceptions.InstrumentoDuplicadoException;
import springbootApp.java.exceptions.InstrumentoNoEncontradoException;
import java.util.ArrayList;
import java.util.List;

public class Inversor implements Observer{
    private String nombre;
    private String dni;
    private List<InstrumentoFinanciero> instrumentosDelInversor;

    public Inversor(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
        instrumentosDelInversor = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private void setDni(String dni) {
        this.dni = dni;
    }

    public void suscribirse(InstrumentoFinanciero instrumento) throws InstrumentoDuplicadoException {
        if (tieneInstrumento(instrumento)) {
            throw new InstrumentoDuplicadoException("Error. Ya estas suscripto a este instrumento");
        }
        instrumentosDelInversor.add(instrumento);
    }

    public void desuscribirse(InstrumentoFinanciero instrumento) throws InstrumentoNoEncontradoException {
        if (!tieneInstrumento(instrumento)) {
            throw new InstrumentoNoEncontradoException("Error. Instrumento no encontrado");
        }
        instrumentosDelInversor.remove(instrumento);
    }

    private boolean tieneInstrumento(InstrumentoFinanciero instrumento) {
        if (instrumento == null)
            return false;
        else
            return instrumentosDelInversor.stream().anyMatch(i -> i.equals(instrumento));
    }

    public String getDni() {
        return dni;
    }

    public List<InstrumentoFinanciero> consultarInstrumentos() {

        return instrumentosDelInversor;
    }

    @Override
    public String toString() {
        return "Inversor{" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                '}';
    }


    @Override
    public void actualizarPrecioInstrumento(InstrumentoFinanciero instrumento) {
        System.out.println(getNombre() + ": el precio del instrumento " + instrumento.getNombre() + " cambio a "
                + instrumento.getPrecio());
    }

    public void actualizarInversor(InversorDTO inversor) {
        this.setDni(inversor.getDni());
        this.setNombre(inversor.getNombre());
    }
}