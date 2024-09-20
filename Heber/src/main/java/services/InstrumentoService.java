package services;

import contenedorBoot.ContenedorBoot;
import controllers.InstrumentoController;
import exceptions.InstrumentoDuplicadoException;
import exceptions.InstrumentoNoEncontradoException;
import models.*;
import repositories.InstrumentoRepository;

import java.util.Scanner;

public class InstrumentoService {

    private static InstrumentoService instrumentoService;
    private Scanner scanner = new Scanner(System.in);
    private InstrumentoRepository instrumentoRepository = InstrumentoRepository.getInstance();

    private InstrumentoService() {
        this.scanner = new Scanner(System.in);
    }

    // Método para obtener la única instancia
    public static InstrumentoService getInstance() {
        if (instrumentoService == null) {
            instrumentoService = new InstrumentoService();
        }
        return instrumentoService;
    }

    public InstrumentoFinanciero buscarInstrumentoPorNombre(String nombreInstrumento) throws InstrumentoNoEncontradoException {
        InstrumentoFinanciero instrumento = instrumentoRepository.buscarInstrumentoPorNombre(nombreInstrumento);
        if (instrumento == null) {
            throw new InstrumentoNoEncontradoException("El instrumento con nombre " + nombreInstrumento + " no se encuentra registrado");
        }
        return instrumento;
    }

    public void consultarInstrumentos() {
        instrumentoRepository.consultarInstrumentos();
    }

    public void verificarDuplicado(String nombreInstrumento) {
        if (instrumentoRepository.verificarInstrumentoDuplicado(nombreInstrumento)) {
            throw new InstrumentoDuplicadoException("El instrumento con nombre " + nombreInstrumento + " ya se encuentra registrado");
        }
    }

    public void registrarInstrumento(InstrumentoFinanciero instrumento) {
        instrumentoRepository.registrarInstrumento(instrumento);
    }

    public void eliminarInstrumentoPorNombre(String nombreInstrumento) throws InstrumentoNoEncontradoException {
        boolean eliminado = instrumentoRepository.eliminarInstrumentoPorNombre(nombreInstrumento);
        if (!eliminado) {
            throw new InstrumentoNoEncontradoException("El instrumento con nombre " + nombreInstrumento + " no se encuentra registrado");
        }
    }

}
