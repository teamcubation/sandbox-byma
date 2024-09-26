package springBootProject.java.com.example.proyectoSpringBoot.model;

import springBootProject.java.com.example.proyectoSpringBoot.service.observer.Notificador;

public abstract class InstrumentoFinanciero extends Notificador{
    private String nombre;
    private Double precio;
    private Integer tipo;

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
        setEstado(this.precio, this);
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
}
