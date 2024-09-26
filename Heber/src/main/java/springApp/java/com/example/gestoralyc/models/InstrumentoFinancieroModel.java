package springApp.java.com.example.gestoralyc.models;

public abstract class InstrumentoFinancieroModel {

    private Long id;
    private String nombre;
    private double precio;

    // Constructor vacío
    public InstrumentoFinancieroModel() {
    }

    // Constructor parametrizado
    public InstrumentoFinancieroModel(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "InstrumentoFinancieroModel{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }
}
