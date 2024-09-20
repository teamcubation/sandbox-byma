package consoleApp.repositories;

import consoleApp.modelos.InstrumentoFinanciero;

import java.util.ArrayList;

public class InstrumenroFinancieroRepository {
    private static InstrumenroFinancieroRepository instrumenroFinancieroRepository;
    private ArrayList<InstrumentoFinanciero> instrumentoFinancieroList;

    private InstrumenroFinancieroRepository(){
        this.instrumentoFinancieroList = new ArrayList<>();
    }

    public static InstrumenroFinancieroRepository getInstrumentoFinancieroRepository() {
        if (instrumenroFinancieroRepository == null)
            instrumenroFinancieroRepository = new InstrumenroFinancieroRepository();
        return instrumenroFinancieroRepository;
    }

    public void registrarInstrumento(InstrumentoFinanciero instrumento){
        this.instrumentoFinancieroList.add(instrumento);
    }
    public void eliminarInstrumento(InstrumentoFinanciero instrumento){
        this.instrumentoFinancieroList.remove(instrumento);
    }
    public ArrayList<InstrumentoFinanciero> consultarTodosLosInstrumentos() {
        return this.instrumentoFinancieroList;
    }

    public InstrumentoFinanciero buscarInstrumento(String nombre) {
        InstrumentoFinanciero instrumentoADevolver = null;
        for (InstrumentoFinanciero instrumento : instrumentoFinancieroList) {
            if (instrumento.getNombre().equals(nombre))
                instrumentoADevolver = instrumento;
        }
        return instrumentoADevolver;
    }



}
