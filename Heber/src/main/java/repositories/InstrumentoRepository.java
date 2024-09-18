package repositories;

import exceptions.InstrumentoNoEncontradoException;
import models.InstrumentoFinanciero;

import java.util.ArrayList;

public class InstrumentoRepository {
    private ArrayList<InstrumentoFinanciero> instrumentos;

    public InstrumentoRepository() {
        this.instrumentos = new ArrayList<>();
    }

    public void registrarInstrumento(InstrumentoFinanciero instrumentoFinanciero) {
        instrumentos.add(instrumentoFinanciero);
    }


    public InstrumentoFinanciero buscarInstrumentoPorNombre(String nombre) {
        return instrumentos.stream()
                .filter(instrumento -> instrumento.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    public void consultarInstrumentos() {
        if (instrumentos.isEmpty()) {
            System.out.println("No hay instrumentos financieros registrados.");
        } else {
            System.out.println("Instrumentos financieros registrados:");
            instrumentos.forEach(instrumento -> System.out.println(instrumento.getNombre()));
        }
    }

    public void eliminarInstrumentoPorNombre(String nombreInstrumento) throws InstrumentoNoEncontradoException {
        boolean eliminado = instrumentos.removeIf(instrumento -> instrumento.getNombre().equalsIgnoreCase(nombreInstrumento));

        if (!eliminado) {
            throw new InstrumentoNoEncontradoException("El instrumento con el nombre " + nombreInstrumento + " no se ha encontrado");
        } else {
            System.out.println("Instrumento con el nombre " + nombreInstrumento + " eliminado exitosamente.");
        }
    }
}

