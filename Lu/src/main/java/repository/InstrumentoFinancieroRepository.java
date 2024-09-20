package repository;

import excepciones.InstrumentoDuplicadoException;
import excepciones.InstrumentoNoEncontradoException;
import excepciones.OpcionInvalidaException;
import repository.factory.InstrumentoFinancieroFactory;
import modelo.InstrumentoFinanciero;
import repository.singleton.Singleton;

import java.util.List;
import java.util.Optional;

public class InstrumentoFinancieroRepository {
    private static InstrumentoFinancieroRepository InstrumentoFinancieroRepository;
    List<InstrumentoFinanciero> instrumentosFinancieros;

    private InstrumentoFinancieroRepository() {
        instrumentosFinancieros = Singleton.getInstancia();
    }

    public static InstrumentoFinancieroRepository obtenerInstancia() {
        if (InstrumentoFinancieroRepository == null) {
            InstrumentoFinancieroRepository = new InstrumentoFinancieroRepository();
        }
        return InstrumentoFinancieroRepository;
    }

    public Optional<InstrumentoFinanciero> buscarInstrumentoFinanciero(String nombre) {
        return  instrumentosFinancieros.stream()
                .filter(instrumento -> instrumento.getNombre().equals(nombre))
                .findFirst();
    }

    public InstrumentoFinanciero registrar (String nombre, double precio, int tipo) throws OpcionInvalidaException, InstrumentoDuplicadoException {
        Optional<InstrumentoFinanciero> instrumentoFinanciero = buscarInstrumentoFinanciero(nombre);

        if (instrumentoFinanciero.isPresent()) {
            throw new InstrumentoDuplicadoException("\n-------- Instrumento financiero existente -No podes añadir instrumentos duplicados-. --------\n\n");
        }


        InstrumentoFinanciero nuevoInstrumentoFinanciero = InstrumentoFinancieroFactory.crearInstrumentoFinanciero(tipo);
        nuevoInstrumentoFinanciero.setNombre(nombre);
        nuevoInstrumentoFinanciero.setPrecio(precio);

        instrumentosFinancieros.add(nuevoInstrumentoFinanciero);

        return nuevoInstrumentoFinanciero;
    }

    public List<InstrumentoFinanciero> consultar() {
        return instrumentosFinancieros;
    }

    public InstrumentoFinanciero consultar(String nombre) throws InstrumentoNoEncontradoException {
        Optional<InstrumentoFinanciero> instrumentoFinanciero = buscarInstrumentoFinanciero(nombre);

        if (instrumentoFinanciero.isEmpty()) {
            throw new InstrumentoNoEncontradoException("\n-------- Instrumento Financiero inexistente. --------\n\n");
        }

        return instrumentoFinanciero.get();
    }

    public InstrumentoFinanciero eliminar(String nombre) throws InstrumentoNoEncontradoException {
        Optional<InstrumentoFinanciero> instrumentoFinanciero = buscarInstrumentoFinanciero(nombre);

        if (instrumentoFinanciero.isEmpty()) {
            throw new InstrumentoNoEncontradoException("\n-------- Instrumento Financiero inexistente. --------\n\n");
        }

        instrumentosFinancieros.removeIf(instrumentoFinancieroAEliminar -> instrumentoFinancieroAEliminar.getNombre().equals(nombre));

        return instrumentoFinanciero.get();
    }
}
