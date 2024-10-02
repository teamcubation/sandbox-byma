package springbootApp.app.services.interfaces;

import springbootApp.app.exceptions.InversorExistenteException;
import springbootApp.app.exceptions.InversorNoEncontradoException;
import springbootApp.app.models.InstrumentoFinanciero;
import springbootApp.app.models.Inversor;

import java.util.List;

public interface IInversorService {

    public void registrarInversor(Inversor inversor) throws InversorExistenteException;

    public List<Inversor> consultarTodosLosInversores();

    public Inversor actualizarInversor(Long id, Inversor inversor) throws InversorNoEncontradoException, InversorExistenteException;

    public Inversor consultarInversor(Long id) throws InversorNoEncontradoException;

    public void eliminarInversor(Long id) throws InversorNoEncontradoException;

    public List<InstrumentoFinanciero> consultarInstrumentosDeInversor(Long id) throws InversorNoEncontradoException;
    public void guardarInversor(Inversor inversor);
}
