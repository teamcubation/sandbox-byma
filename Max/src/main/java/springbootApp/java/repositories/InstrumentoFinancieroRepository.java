package springbootApp.java.repositories;

import springbootApp.java.exceptions.InstrumentoDuplicadoException;
import springbootApp.java.exceptions.InstrumentoNoEncontradoException;
import springbootApp.java.DTOs.InstrumentoDTO;
import springbootApp.java.models.InstrumentoFinanciero;
import org.springframework.stereotype.Repository;
import springbootApp.java.repositories.interfaces.IInstrumentoFinancieroRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InstrumentoFinancieroRepository implements IInstrumentoFinancieroRepository {


    private List<InstrumentoFinanciero> instrumentoFinancieroList = new ArrayList<>();

    public void registrarInstrumento(InstrumentoFinanciero instrumento) {
        this.instrumentoFinancieroList.add(instrumento);
    }

    public void eliminarInstrumento(InstrumentoFinanciero instrumento) {
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
}
