package org.example.ejercicioGestionAccionesYBonos.repo;

import org.example.ejercicioGestionAccionesYBonos.modelo.InstrumentoFinanciero;

public interface InstrumentoFinancieroObserver {
    void actualizar(InstrumentoFinanciero instrumentoFinanciero);
}
