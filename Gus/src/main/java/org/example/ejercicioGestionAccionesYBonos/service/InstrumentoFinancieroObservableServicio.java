package org.example.ejercicioGestionAccionesYBonos.service;

import org.example.ejercicioGestionAccionesYBonos.modelo.InstrumentoFinanciero;
import org.example.ejercicioGestionAccionesYBonos.repo.InstrumentoFinancieroObservableImp;
import org.example.ejercicioGestionAccionesYBonos.repo.InstrumentoFinancieroObserver;

public class InstrumentoFinancieroObservableServicio {

    private InstrumentoFinancieroObservableImp instrumentoFinancieroObservableImp;

    private static InstrumentoFinancieroObservableServicio instancia;

    public InstrumentoFinancieroObservableServicio(InstrumentoFinancieroObservableImp instrumentoFinancieroObservableImp) {
        this.instrumentoFinancieroObservableImp = instrumentoFinancieroObservableImp;
    }

    public static InstrumentoFinancieroObservableServicio getInstancia(InstrumentoFinancieroObservableImp instrumentoFinancieroObservableImp) {
        if (instancia == null) {
            return new InstrumentoFinancieroObservableServicio(instrumentoFinancieroObservableImp);
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
