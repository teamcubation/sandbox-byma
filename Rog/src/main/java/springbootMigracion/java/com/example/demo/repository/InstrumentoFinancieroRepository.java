package springbootMigracion.java.com.example.demo.repository;

import org.springframework.stereotype.Repository;
import springbootMigracion.java.com.example.demo.exception.InstrumentoDuplicadoException;
import springbootMigracion.java.com.example.demo.exception.InstrumentoNoEncontradoException;
import springbootMigracion.java.com.example.demo.model.InstrumentoFinanciero;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InstrumentoFinancieroRepository implements IInstrumentoFinancieroRepository {

    private final List<InstrumentoFinanciero> instrumentoFinancieroList = new ArrayList<>();

    private InstrumentoFinancieroRepository() {}

    @Override
    public void agregarInstrumento(InstrumentoFinanciero instrumento) {
        if (buscarInstrumentoPorNombre(instrumento.getNombre()).isPresent()){
            throw new InstrumentoDuplicadoException("EL instrumento ya existe.");
        }
        instrumentoFinancieroList.add(instrumento);
    }

    @Override
    public List<InstrumentoFinanciero> listarTodosLosInstrumentos() {
        return instrumentoFinancieroList;
    }

    @Override
    public Optional<InstrumentoFinanciero> buscarInstrumentoPorNombre(String nombre) {
        return instrumentoFinancieroList.stream()
                .filter(instrumento -> instrumento.getNombre().equalsIgnoreCase(nombre))
                .findFirst();
    }

    @Override
    public void eliminarInstrumento(String nombre) {
        Optional<InstrumentoFinanciero> instrumentoExistente = buscarInstrumentoPorNombre(nombre);
        if (instrumentoExistente.isEmpty()){
            throw new InstrumentoNoEncontradoException("Instrumento no econtrado.");
        }
        InstrumentoFinanciero instrumento = instrumentoExistente.get();
        instrumentoFinancieroList.remove(instrumento);
    }

    @Override
    public void editarInstrumento(String nombre, InstrumentoFinanciero nuevoInstrumento) {
        InstrumentoFinanciero instrumentoExistente = buscarInstrumentoPorNombre(nombre)
                .orElseThrow(() -> new InstrumentoNoEncontradoException("Instrumento no encontrado."));
        instrumentoFinancieroList.remove(instrumentoExistente);
        instrumentoFinancieroList.add(nuevoInstrumento);
    }

}
