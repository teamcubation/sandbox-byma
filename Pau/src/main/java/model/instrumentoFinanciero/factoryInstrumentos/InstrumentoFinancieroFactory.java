package main.java.model.instrumentoFinanciero.factoryInstrumentos;

import main.java.model.instrumentoFinanciero.InstrumentoFinanciero;

import java.time.LocalDate;

public interface InstrumentoFinancieroFactory {
    public InstrumentoFinanciero createInstrumentoFinanciero(String nombre, Double precio, LocalDate fechaDeEmision);
}
