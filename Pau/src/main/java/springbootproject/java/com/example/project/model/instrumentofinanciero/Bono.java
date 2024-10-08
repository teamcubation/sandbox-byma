package com.example.project.model.instrumentofinanciero;

import com.example.project.utils.Validador;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@DiscriminatorValue(value = "BONO")
public class Bono extends InstrumentoFinanciero {

    public void setNombre(String nombre) {
        Validador.validarNombreBono(nombre);
        this.nombre = nombre;
    }

}
