package consoleversion.model.instrumentoFinanciero.factoryInstrumentos;

import consoleversion.model.instrumentoFinanciero.InstrumentoFinanciero;

import java.time.LocalDate;

public interface InstrumentoFinancieroFactory {
    public InstrumentoFinanciero createInstrumentoFinanciero(String nombre, Double precio, LocalDate fechaDeEmision);
}
