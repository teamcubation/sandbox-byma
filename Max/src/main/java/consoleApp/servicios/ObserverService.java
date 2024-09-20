package consoleApp.servicios;

import consoleApp.excepciones.InversorNoEncontradoException;
import consoleApp.modelos.InstrumentoFinanciero;
import consoleApp.modelos.Inversor;
import consoleApp.repositories.InstrumenroFinancieroRepository;
import consoleApp.repositories.InversorRepository;

import java.util.ArrayList;

public class ObserverService {
    private static ObserverService observerService;
    private InversorRepository inversoresRepository;
    private InstrumenroFinancieroRepository instrumentosRepository;

    private ObserverService() {
        this.inversoresRepository = InversorRepository.getInversorRepository();
        this.instrumentosRepository = InstrumenroFinancieroRepository.getInstrumentoFinancieroRepository();
    }

    public static ObserverService getObserverService() {
        if (observerService == null) {
            observerService = new ObserverService();
        }
        return observerService;
    }
    public void metodoParaSuscribirse(String dni, String nombreInstrumento) {
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

    public void metodoParaDesuscribirse(String dni, String nombreInstrumento) {
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

    public static void notificarCambioDePrecio(ArrayList<Inversor> inversoresANotificar,
                                               InstrumentoFinanciero instrumento) {
        for (Inversor inversor : inversoresANotificar) {
            inversor.actualizar(instrumento);
        }
    }

}