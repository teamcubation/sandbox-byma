package springbootApp.java.models;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import springbootApp.java.exceptions.InstrumentoDuplicadoException;
import springbootApp.java.exceptions.InstrumentoNoEncontradoException;
import springbootApp.java.exceptions.InversorExistenteException;
import springbootApp.java.services.ObserverService;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
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

    public List<Inversor> getInversoresList() {
        return inversoresList;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "InstrumentoFinanciero{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", tipo=" + tipo + "\n inversoresList=" + inversoresList +
                '}';
    }

}
