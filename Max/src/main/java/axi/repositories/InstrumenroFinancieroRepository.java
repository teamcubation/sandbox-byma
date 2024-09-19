package axi.repositories;

import axi.modelos.InstrumentoFinanciero;

import java.util.ArrayList;

public class InstrumenroFinancieroRepository {
    private static InstrumenroFinancieroRepository instrumenroFinancieroRepository;
    private ArrayList<InstrumentoFinanciero>instrumentos;

    private InstrumenroFinancieroRepository(){
        this.instrumentos = new ArrayList<>();
    }

    public static InstrumenroFinancieroRepository getInstrumentoFinancieroRepository() {
        if (instrumenroFinancieroRepository == null)
            instrumenroFinancieroRepository = new InstrumenroFinancieroRepository();
        return instrumenroFinancieroRepository;
    }

    public void registrarInstrumento(InstrumentoFinanciero instrumento){
        this.instrumentos.add(instrumento);
    }
    public void eliminarInstrumento(InstrumentoFinanciero instrumento){
        this.instrumentos.remove(instrumento);
    }
    public void consultarTodosLosInstrumentos() {
        for (InstrumentoFinanciero instrumento : instrumentos) {
            System.out.println(instrumento);
        }
    }

    public InstrumentoFinanciero buscarInstrumento(String nombre) {
        InstrumentoFinanciero instrumentoADevolver = null;
        for (InstrumentoFinanciero instrumento : instrumentos) {
            if (instrumento.getNombre().equals(nombre))
                instrumentoADevolver = instrumento;
        }
        return instrumentoADevolver;
    }
    public void modificarInstrumento(InstrumentoFinanciero instrumento){
        this.eliminarInstrumento(this.buscarInstrumento(instrumento.getNombre()));
        this.registrarInstrumento(instrumento);
    }


}
