package org.example.ejercicioGestionAccionesYBonos.repo;

import org.example.ejercicioGestionAccionesYBonos.modelo.InstrumentoFinanciero;

public interface InstrumentoFinancieroObservable {

    void registrarObservador (InstrumentoFinancieroObserver instrumentoFinancieroObserver);
    void eliminarObservador (InstrumentoFinancieroObserver instrumentoFinancieroObserver);
    void notificarObservadores (InstrumentoFinanciero instrumentoFinanciero);
}
