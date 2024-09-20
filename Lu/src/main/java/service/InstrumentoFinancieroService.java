package service;


import excepciones.InstrumentoDuplicadoException;
import excepciones.InstrumentoNoEncontradoException;
import excepciones.OpcionInvalidaException;
import modelo.InstrumentoFinanciero;
import repository.InstrumentoFinancieroRepository;

import java.util.List;

public class InstrumentoFinancieroService {
    private static InstrumentoFinancieroService instrumentoFinancieroService;
    private final InstrumentoFinancieroRepository instrumentosFinancierosRepository;

    private InstrumentoFinancieroService() {
        instrumentosFinancierosRepository = InstrumentoFinancieroRepository.obtenerInstancia();
    }

    public static InstrumentoFinancieroService obtenerInstancia() {
        if (instrumentoFinancieroService == null) {
            instrumentoFinancieroService = new InstrumentoFinancieroService();
        }
        return instrumentoFinancieroService;
    }

    public List<InstrumentoFinanciero> consultar() {
        return instrumentosFinancierosRepository.consultar();
    }

    public InstrumentoFinanciero consultar(String nombre) throws InstrumentoNoEncontradoException {
        return this.instrumentosFinancierosRepository.consultar(nombre);
    }

    public InstrumentoFinanciero eliminar(String nombre) throws InstrumentoNoEncontradoException {
        return this.instrumentosFinancierosRepository.eliminar(nombre);
    }

    public InstrumentoFinanciero registrar(String nombre, double precio, int tipo) throws OpcionInvalidaException, InstrumentoDuplicadoException {
        return this.instrumentosFinancierosRepository.registrar(nombre, precio, tipo);
    }
}
