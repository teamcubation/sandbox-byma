package springbootApp.java.repositories;

import springbootApp.java.exceptions.InstrumentoDuplicadoException;
import springbootApp.java.exceptions.InstrumentoNoEncontradoException;
import springbootApp.java.models.InstrumentoDTO;
import springbootApp.java.models.InstrumentoFinanciero;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InstrumentoFinancieroRepository {


    private List<InstrumentoFinanciero> instrumentoFinancieroList = new ArrayList<>();


    public void registrarInstrumento(InstrumentoFinanciero instrumento){
        this.instrumentoFinancieroList.add(instrumento);
    }
    public void eliminarInstrumento(InstrumentoFinanciero instrumento){
        this.instrumentoFinancieroList.remove(instrumento);
    }
    public List<InstrumentoFinanciero> consultarTodosLosInstrumentos() {
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
        instrumentoEncontrado.actualizarInstrumento(instrumento);
    }

    private boolean instrumentoExistente(String nombre) {
        return this.buscarInstrumento(nombre) != null;
    }
}
