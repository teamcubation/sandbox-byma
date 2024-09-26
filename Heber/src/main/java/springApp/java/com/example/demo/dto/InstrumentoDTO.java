package springApp.java.com.example.demo.dto;

import springApp.java.com.example.demo.models.TipoInstrumento;

public class InstrumentoDTO {
    //el controller recibe un InstrumentoDTO y devuelve un InstrumentoDTO
    private TipoInstrumento tipo; // ACCION, BONO, etc si en un futuro se agregan agregar al ENUM.
    private String nombre;
    private double precio;
    private Double dividendo; // Solo para Accion
    private Double tasaInteres; // Solo para Bono
    //TODO: Agregar fecha de creacion y parking

    // Getters y Setters

    public TipoInstrumento getTipo() {
        return tipo;
    }

    public void setTipo(TipoInstrumento tipo) {
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

    public Double getDividendo() {
        return dividendo;
    }

    public void setDividendo(Double dividendo) {
        this.dividendo = dividendo;
    }

    public Double getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(Double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }
}
