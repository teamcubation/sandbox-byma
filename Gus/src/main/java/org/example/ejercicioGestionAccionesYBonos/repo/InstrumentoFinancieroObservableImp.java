package org.example.ejercicioGestionAccionesYBonos.repo;

import org.example.ejercicioGestionAccionesYBonos.modelo.InstrumentoFinanciero;

import java.util.ArrayList;

public class InstrumentoFinancieroObservableImp implements InstrumentoFinancieroObservable {

    private ArrayList<InstrumentoFinancieroObserver> listaDeInversores;

    private static InstrumentoFinancieroObservableImp instancia;

    private InstrumentoFinancieroObservableImp() {
        this.listaDeInversores = new ArrayList<>();
    }

    public static InstrumentoFinancieroObservableImp getInstancia() {
        if (instancia == null) {
            return new InstrumentoFinancieroObservableImp();
        }

        return instancia;
    }

    @Override
    public void registrarObservador(InstrumentoFinancieroObserver instrumentoFinancieroObserver) {
        this.listaDeInversores.add(instrumentoFinancieroObserver);
    }

    @Override
    public void eliminarObservador(InstrumentoFinancieroObserver instrumentoFinancieroObserver) {
        this.listaDeInversores.remove(instrumentoFinancieroObserver);
    }

    @Override
    public void notificarObservadores(InstrumentoFinanciero instrumentoFinanciero) {
        this.listaDeInversores.forEach(i -> i.actualizar(instrumentoFinanciero));
    }
}
