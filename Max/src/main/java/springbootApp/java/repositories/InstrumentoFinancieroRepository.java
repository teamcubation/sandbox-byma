package springbootApp.java.repositories;

import springbootApp.java.models.InstrumentoFinanciero;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class InstrumentoFinancieroRepository {
    private static InstrumentoFinancieroRepository instrumentoFinancieroRepository;
    private ArrayList<InstrumentoFinanciero> instrumentoFinancieroList;

    private InstrumentoFinancieroRepository(){
        this.instrumentoFinancieroList = new ArrayList<>();
    }

    public static InstrumentoFinancieroRepository getInstrumentoFinancieroRepository() {
        if (instrumentoFinancieroRepository == null)
            instrumentoFinancieroRepository = new InstrumentoFinancieroRepository();
        return instrumentoFinancieroRepository;
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

    public void modificarInstrumento(String nombre, InstrumentoFinanciero instrumento) {
        InstrumentoFinanciero instrumentoEncontrado = this.buscarInstrumento(nombre);
        instrumentoEncontrado.actualizar(instrumento);
    }
}
