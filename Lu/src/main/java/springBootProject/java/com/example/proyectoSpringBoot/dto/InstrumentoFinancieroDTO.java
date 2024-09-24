package springBootProject.java.com.example.proyectoSpringBoot.dto;

public class InstrumentoFinancieroDTO {
    private String nombre;
    private double precio;
    private int tipo;

    public InstrumentoFinancieroDTO(String nombre, double precio, int tipo) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
