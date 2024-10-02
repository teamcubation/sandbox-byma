package springbootApp.app.services.interfaces;

import springbootApp.app.exceptions.InstrumentoNoEncontradoException;
import springbootApp.app.exceptions.InversorNoEncontradoException;


public interface IObserverService {


    void metodoParaDesuscribirse(Long id, Long idInstrumento) throws InversorNoEncontradoException, InstrumentoNoEncontradoException;
    void metodoParaSuscribirse(Long idInversor, Long idInstrumento) throws InversorNoEncontradoException, InstrumentoNoEncontradoException;

}