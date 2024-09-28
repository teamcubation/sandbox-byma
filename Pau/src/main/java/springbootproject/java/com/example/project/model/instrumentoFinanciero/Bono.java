package com.example.project.model.instrumentoFinanciero;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "BONO")
public class Bono extends InstrumentoFinanciero {

    public Bono() {
        super();
    }
}
