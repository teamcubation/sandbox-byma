package springbootApp.java.repositories.interfaces;

import springbootApp.java.exceptions.InstrumentoDuplicadoException;
import springbootApp.java.exceptions.InstrumentoNoEncontradoException;
import springbootApp.java.DTOs.InstrumentoDTO;
import springbootApp.java.models.InstrumentoFinanciero;

import java.util.List;

public interface IInstrumentoFinancieroRepository {

    public void registrarInstrumento(InstrumentoFinanciero instrumento);

    public InstrumentoFinanciero buscarInstrumento(String nombre);

    public void eliminarInstrumento(InstrumentoFinanciero instrumento);

    public List<InstrumentoFinanciero> consultarTodosLosInstrumentos();
}
