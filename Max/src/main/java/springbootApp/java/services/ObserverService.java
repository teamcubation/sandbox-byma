package springbootApp.java.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootApp.java.exceptions.InstrumentoDuplicadoException;
import springbootApp.java.exceptions.InstrumentoNoEncontradoException;
import springbootApp.java.exceptions.InversorExistenteException;
import springbootApp.java.exceptions.InversorNoEncontradoException;
import springbootApp.java.models.InstrumentoFinanciero;
import springbootApp.java.models.Inversor;
import springbootApp.java.repositories.InstrumentoFinancieroRepository;
import springbootApp.java.repositories.InversorRepository;

import java.util.List;
@Service
public class ObserverService {
    @Autowired
    private InversorRepository inversoresRepository;
    @Autowired
    private InstrumentoFinancieroRepository instrumentosRepository;


    public void metodoParaSuscribirse(String dni, String nombreInstrumento) throws InversorNoEncontradoException, InversorExistenteException, InstrumentoDuplicadoException {
        InstrumentoFinanciero instrumento = instrumentosRepository.buscarInstrumento(nombreInstrumento);
        if (instrumento == null) {
            throw new InversorNoEncontradoException("Error. instrumento no existente");
        }
        Inversor inversor = inversoresRepository.buscarInversor(dni);
        if (inversor == null) {
            throw new InversorNoEncontradoException("Error. inversor no existente");
        }
        instrumento.suscribirse(inversor);
    }

    public void metodoParaDesuscribirse(String dni, String nombreInstrumento) throws InversorNoEncontradoException, InstrumentoNoEncontradoException {
        InstrumentoFinanciero instrumento = instrumentosRepository.buscarInstrumento(nombreInstrumento);
        if (instrumento == null) {
            throw new InversorNoEncontradoException("Error. instrumento no existente");
        }
        Inversor inversor = inversoresRepository.buscarInversor(dni);
        if (inversor == null) {
            throw new InversorNoEncontradoException("Error. inversor no existente");
        }
        instrumento.desuscribirse(inversor);
    }

    public static void notificarCambioDePrecio(List<Inversor> inversoresANotificar,
                                               InstrumentoFinanciero instrumento) {
        for (Inversor inversor : inversoresANotificar) {
            inversor.actualizarPrecioInstrumento(instrumento);
        }
    }
}
