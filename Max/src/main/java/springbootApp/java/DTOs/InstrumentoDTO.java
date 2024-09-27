package springbootApp.java.DTOs;

import springbootApp.java.models.Tipo;

public class InstrumentoDTO {
    private String nombre;
    private Double precio;
    private Tipo tipo;
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

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

}