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

    public void consultarInstrumentosDeInversor(String dni) {
        Inversor inversor = inversoresRepository.buscarInversor(dni);
        if (inversor == null) {
            throw new InversorNoEncontradoException("Error. Inversor no encontrado");
        }
        inversor.consultarInstrumentos();
    }
    public void eliminarInversor(String dni) {
        Inversor inversor = inversoresRepository.buscarInversor(dni);
        if (inversor == null) {
            throw new InversorNoEncontradoException("Error. Inversor no encontrado");
        }
        inversoresRepository.borrarInversor(inversor);
    }

    public void modificarInversor(String variable, String modificacion, String dni) {
        Inversor inversor = inversoresRepository.buscarInversor(dni);
        if (inversor == null) {
            throw new InversorNoEncontradoException("Error. Inversor no encontrado");
        }
        switch (variable) {
            case "1":
                inversor.setNombre(modificacion);
                break;
            case "2":
                inversor.setDni(modificacion);
                break;
            default:
                throw new IllegalArgumentException("Error. los campos no coinciden");
        }
    }
}
