package com.example.project.model.instrumentoFinanciero;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "ACCION")
public class Accion extends InstrumentoFinanciero {

    public Accion() {
        super();
    }
}
