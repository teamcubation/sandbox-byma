package springbootproject.java.com.example.project.controller.dto;

import java.time.LocalDate;

public class EditarInstrumentoDTO {
    private String nuevoNombre;
    private Double nuevoPrecio;
    private LocalDate nuevaFechaDeEmision;

    public String getNuevoNombre() {
        return nuevoNombre;
    }

    public void setNuevoNombre(String nuevoNombre) {
        this.nuevoNombre = nuevoNombre;
    }

    public Double getNuevoPrecio() {
        return nuevoPrecio;
    }

    public void setNuevoPrecio(Double nuevoPrecio) {
        this.nuevoPrecio = nuevoPrecio;
    }

    public LocalDate getNuevaFechaDeEmision() {
        return nuevaFechaDeEmision;
    }

    public void setNuevaFechaDeEmision(LocalDate nuevaFechaDeEmision) {
        this.nuevaFechaDeEmision = nuevaFechaDeEmision;
    }
}
