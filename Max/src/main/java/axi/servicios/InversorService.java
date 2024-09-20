package axi.servicios;

import axi.excepciones.InversorNoEncontradoException;
import axi.modelos.InstrumentoFinanciero;
import axi.modelos.Inversor;
import axi.repositories.InstrumenroFinancieroRepository;
import axi.repositories.InversorRepository;

import java.util.ArrayList;

public class InversorService {
    private static InversorService inversorService;
    private InversorRepository inversoresRepository;
    private InstrumenroFinancieroRepository instrumentosRepository;

    private InversorService() {
        this.inversoresRepository = InversorRepository.getInversorRepository();
        this.instrumentosRepository = InstrumenroFinancieroRepository.getInstrumentoFinancieroRepository();
    }

    public static InversorService getInversorService() {
        if (inversorService == null) {
            inversorService = new InversorService();
        }
        return inversorService;
    }

    public void registrarInversor(String nombre, String dni) {
        Inversor inversor = inversoresRepository.buscarInversor(dni);
        if (inversor != null)
            throw new RuntimeException("Error. Inversor existente");
        inversoresRepository.registrarInversor(new Inversor(nombre, dni));
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

    public void consultarInstrumentosDeInversor(String dni) {
        Inversor inversor = inversoresRepository.buscarInversor(dni);
        if (inversor == null) {
            throw new InversorNoEncontradoException("Error. Inversor no encontrado");
        }
        inversor.consultarInstrumentos();
    }
}
