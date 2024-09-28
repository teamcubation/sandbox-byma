package com.example.project.controller.dto;

import com.example.project.exceptions.NoExisteEseTipoDeInstrumentoException;
import com.example.project.model.instrumentoFinanciero.TipoInstrumentoFinanciero;

import java.time.LocalDate;

public class InstrumentoFinancieroDTO {
    private String nombre;
    private Double precio;
    private LocalDate fechaDeEmision;
    private String stringTipoInstrumentoFinanciero;

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

    public LocalDate getFechaDeEmision() {
        return fechaDeEmision;
    }

    public void setFechaDeEmision(LocalDate fechaDeEmision) {
        this.fechaDeEmision = fechaDeEmision;
    }

    public String getStringTipoInstrumentoFinanciero() {
        return stringTipoInstrumentoFinanciero;
    }

    public void setStringTipoInstrumentoFinanciero(String stringTipoInstrumentoFinanciero) {
        this.stringTipoInstrumentoFinanciero = stringTipoInstrumentoFinanciero;
    }

    public TipoInstrumentoFinanciero getTipoInstrumentoFinanciero() throws NoExisteEseTipoDeInstrumentoException {
        if (this.stringTipoInstrumentoFinanciero.equalsIgnoreCase("accion")) {
            return TipoInstrumentoFinanciero.ACCION;
        } else if (this.stringTipoInstrumentoFinanciero.equalsIgnoreCase("bono")) {
            return TipoInstrumentoFinanciero.BONO;
        } else {
            throw new NoExisteEseTipoDeInstrumentoException("El tipo ingresado no corresponde a un tipo de instrumento conocido.");
        }
   }

}
