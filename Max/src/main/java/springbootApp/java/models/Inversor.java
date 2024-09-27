package springbootApp.java.models;
import springbootApp.java.exceptions.InstrumentoDuplicadoException;
import springbootApp.java.exceptions.InstrumentoNoEncontradoException;
import java.util.ArrayList;
import java.util.List;

public class Inversor{
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public List<InstrumentoFinanciero> getInstrumentosInversor() {
        return instrumentosDelInversor;
    }

    @Override
    public String toString() {
        return "Inversor{" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                '}';
    }
}