package springbootApp.java.repositories;

import springbootApp.java.exceptions.InstrumentoDuplicadoException;
import springbootApp.java.exceptions.InstrumentoNoEncontradoException;
import springbootApp.java.models.InstrumentoDTO;
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

    public void modificarInstrumento(String nombre, InstrumentoDTO instrumento) throws InstrumentoNoEncontradoException, InstrumentoDuplicadoException {
        InstrumentoFinanciero instrumentoEncontrado = this.buscarInstrumento(nombre);
        if (instrumentoEncontrado == null) {
            throw new InstrumentoNoEncontradoException("Error. Instrumento no encontrado");
        }
        if (instrumentoExistente(instrumento.getNombre())) {
            throw new InstrumentoDuplicadoException("Error. Instrumento con nombre existente");
        }
        instrumentoEncontrado.actualizar(instrumento);
    }

    private boolean instrumentoExistente(String nombre) {
        return this.buscarInstrumento(nombre) != null;
    }
}
