package springbootproject.java.com.example.project.model.instrumentoFinanciero.factoryInstrumentos;

import springbootproject.java.com.example.project.model.instrumentoFinanciero.InstrumentoFinanciero;

import java.time.LocalDate;

public interface InstrumentoFinancieroFactory {
    public InstrumentoFinanciero createInstrumentoFinanciero(String nombre, Double precio, LocalDate fechaDeEmision);
}
