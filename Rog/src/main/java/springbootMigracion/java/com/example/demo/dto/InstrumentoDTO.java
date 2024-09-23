package springbootMigracion.java.com.example.demo.dto;

public class InstrumentoDTO {
    private String nombre;
    private double precio;
    private String tipo;
    private Double tasaDeInteres;


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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getTasaDeInteres() {
        return tasaDeInteres;
    }

    public void setTasaDeInteres(Double tasaDeInteres) {
        this.tasaDeInteres = tasaDeInteres;
    }
}
