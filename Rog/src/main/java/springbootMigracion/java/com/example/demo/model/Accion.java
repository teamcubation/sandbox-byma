package springbootMigracion.java.com.example.demo.model;

public class Accion extends InstrumentoFinanciero {
    public Accion(String nombre, Double precio) {
        super(nombre, precio, "Acción");
    }

    @Override
    public String toString() {
        return super.toString() + "} ";
    }
}
