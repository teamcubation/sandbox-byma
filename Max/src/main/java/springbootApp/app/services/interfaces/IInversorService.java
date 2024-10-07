package springbootApp.app.services.interfaces;

import springbootApp.app.exceptions.InversorExistenteException;
import springbootApp.app.exceptions.InversorNoEncontradoException;
import springbootApp.app.models.InstrumentoFinanciero;
import springbootApp.app.models.Inversor;

import java.util.List;

public interface IInversorService {

    void registrarInversor(Inversor inversor) throws InversorExistenteException;

    List<Inversor> consultarTodosLosInversores();

    Inversor actualizarInversor(Long id, Inversor inversor) throws InversorNoEncontradoException, InversorExistenteException;

    Inversor consultarInversor(Long id) throws InversorNoEncontradoException;

    void eliminarInversor(Long id) throws InversorNoEncontradoException;

    List<InstrumentoFinanciero> consultarInstrumentosDeInversor(Long id) throws InversorNoEncontradoException;
    void guardarInversor(Inversor inversor);
}
