package com.example.project.model.instrumentofinanciero;

import com.example.project.utils.Validador;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue(value = "ACCION")
public class Accion extends InstrumentoFinanciero {
    @Override
    public void setNombre(String nombre) {
        Validador.validarNombreAccion(nombre);
        this.nombre = nombre;
    }
}
