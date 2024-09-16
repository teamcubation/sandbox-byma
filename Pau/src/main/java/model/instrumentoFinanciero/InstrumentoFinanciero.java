package main.java.model.instrumentoFinanciero;

import java.time.LocalDate;

public abstract class InstrumentoFinanciero {
    private String nombre;
    private Double precio;
    private LocalDate fechaDeEmision;

    public InstrumentoFinanciero() {
    }

    public String getNombre() {
        return nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    // Aca imagino que se podría usar una expresion regular para validar el nombre del instrumento
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(Double precio) throws IllegalArgumentException{
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.precio = precio;
    }

    public LocalDate getFechaDeEmision() {
        return fechaDeEmision;
    }

    public void setFechaDeEmision(LocalDate fechaDeEmision) {
        this.fechaDeEmision = fechaDeEmision;
    }
}
