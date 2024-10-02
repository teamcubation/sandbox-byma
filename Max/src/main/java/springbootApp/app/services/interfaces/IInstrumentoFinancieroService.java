package springbootApp.app.services.interfaces;

import springbootApp.app.exceptions.InstrumentoDuplicadoException;
import springbootApp.app.exceptions.InstrumentoNoEncontradoException;
import springbootApp.app.models.InstrumentoFinanciero;

import java.util.List;

public interface IInstrumentoFinancieroService {

    void registrarInstrumentoFinanciero(InstrumentoFinanciero instrumento) throws InstrumentoDuplicadoException;
    List<InstrumentoFinanciero> consultarTodosLosInstrumentos();

    InstrumentoFinanciero buscarInstrumentoPorID(Long id) throws InstrumentoNoEncontradoException;

    InstrumentoFinanciero buscarInstrumentoPorNombre(String nombre) throws InstrumentoNoEncontradoException;

    InstrumentoFinanciero actualizarInstrumento(Long id, InstrumentoFinanciero instrumento) throws InstrumentoNoEncontradoException, InstrumentoDuplicadoException;

    void eliminarInstrumento(Long id) throws InstrumentoNoEncontradoException;
    void guardarInstrumento(InstrumentoFinanciero instrumento);

}
