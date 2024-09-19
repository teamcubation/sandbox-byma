package org.example.ejercicioGestionAccionesYBonos.service;

import org.example.ejercicioGestionAccionesYBonos.modelo.InstrumentoFinanciero;
import org.example.ejercicioGestionAccionesYBonos.repo.InstrumentoFinancieroObservableImp;
import org.example.ejercicioGestionAccionesYBonos.repo.InstrumentoFinancieroObserver;

public class NotificacionInstrumentoServicio {

    private static NotificacionInstrumentoServicio instancia;
    private InstrumentoFinancieroObservableImp instrumentoFinancieroObservableImp;

    public NotificacionInstrumentoServicio(InstrumentoFinancieroObservableImp instrumentoFinancieroObservableImp) {
        this.instrumentoFinancieroObservableImp = instrumentoFinancieroObservableImp;
    }

    public static NotificacionInstrumentoServicio getInstancia(InstrumentoFinancieroObservableImp instrumentoFinancieroObservableImp) {
        if (instancia == null) {
            return new NotificacionInstrumentoServicio(instrumentoFinancieroObservableImp);
        }

        return instancia;
    }


    //No se me ocurrio alguna logica de negocio para aplicar en el servicio observable

    public void registrarObservador(InstrumentoFinancieroObserver instrumentoFinancieroObserver) {
        this.instrumentoFinancieroObservableImp.registrarObservador(instrumentoFinancieroObserver);
    }

    public void eliminarObservador(InstrumentoFinancieroObserver instrumentoFinancieroObserver) {
        this.instrumentoFinancieroObservableImp.eliminarObservador(instrumentoFinancieroObserver);
    }

    public void notificarObservadores(InstrumentoFinanciero instrumentoFinanciero) {
        this.instrumentoFinancieroObservableImp.notificarObservadores(instrumentoFinanciero);
    }


}
