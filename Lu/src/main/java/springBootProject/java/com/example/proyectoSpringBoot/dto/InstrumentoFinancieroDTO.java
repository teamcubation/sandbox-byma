package springBootProject.java.com.example.proyectoSpringBoot.dto;

public class InstrumentoFinancieroDTO {
    private String nombre;
    private Double precio;
    private Integer tipo;

    public InstrumentoFinancieroDTO(String nombre, Double precio, Integer tipo) {
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
}
