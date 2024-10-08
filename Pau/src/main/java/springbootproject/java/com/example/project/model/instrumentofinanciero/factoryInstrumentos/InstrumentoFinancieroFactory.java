package com.example.project.model.instrumentofinanciero.factoryInstrumentos;

import com.example.project.model.instrumentofinanciero.InstrumentoFinanciero;

import java.time.LocalDate;

public interface InstrumentoFinancieroFactory {
    public InstrumentoFinanciero createInstrumentoFinanciero(String nombre, Double precio, LocalDate fechaDeEmision);
}
