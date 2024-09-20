package org.example.repository;

import org.example.exception.InstrumentoNoEncontradoException;
import org.example.exception.InstrumentoDuplicadoException;
import org.example.model.InstrumentoFinanciero;

import java.util.ArrayList;
import java.util.List;

public class InstrumentoFinancieroRepository implements IInstrumentoFinancieroRepository {

    private final List<InstrumentoFinanciero> instrumentoFinancieroList = new ArrayList<>();
    private static InstrumentoFinancieroRepository instrumentoFinancieroRepository;

    private InstrumentoFinancieroRepository() {}

    public static InstrumentoFinancieroRepository getInstance() {
        if (instrumentoFinancieroRepository == null) {
            instrumentoFinancieroRepository = new InstrumentoFinancieroRepository();
        }
        return instrumentoFinancieroRepository;
    }

    @Override
    public void agregarInstrumento(InstrumentoFinanciero instrumento) {
        if (buscarInstrumentoPorNombre(instrumento.getNombre()) != null){
            throw new InstrumentoDuplicadoException("EL instrumento ya existe.");
        }
        instrumentoFinancieroList.add(instrumento);
    }

    @Override
    public List<InstrumentoFinanciero> listarTodosLosInstrumentos() {
        return instrumentoFinancieroList;
    }

    @Override
    public InstrumentoFinanciero buscarInstrumentoPorNombre(String nombre) {
        return instrumentoFinancieroList.stream()
                .filter(instrumento -> instrumento.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void eliminarInstrumento(String nombre) {
        InstrumentoFinanciero instrumentoExistente = buscarInstrumentoPorNombre(nombre);
        if (instrumentoExistente == null){
            throw new InstrumentoNoEncontradoException("Instrumento no econtrado.");
        }
        instrumentoFinancieroList.remove(instrumentoExistente);
    }

}
