package consoleApp.repositories;

import springApp.java.com.example.gestoralyc.exceptions.InstrumentoDuplicadoException;
import consoleApp.models.InstrumentoFinanciero;

import java.util.ArrayList;

public class InstrumentoRepository {
    private static InstrumentoRepository instrumentoRepository;
    private ArrayList<InstrumentoFinanciero> instrumentos;

    private InstrumentoRepository() {
        this.instrumentos = new ArrayList<>();
    }

    public static InstrumentoRepository getInstance() {
        if (instrumentoRepository == null) {
            instrumentoRepository = new InstrumentoRepository();
        }
        return instrumentoRepository;
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

    public boolean verificarInstrumentoDuplicado(String nombre) throws InstrumentoDuplicadoException {
        boolean duplicado = instrumentos.stream()
                .anyMatch(instrumento -> instrumento.getNombre().equalsIgnoreCase(nombre));
        return duplicado;
    }

    public void consultarInstrumentos() {
        if (instrumentos.isEmpty()) {
            System.out.println("No hay instrumentos financieros registrados.");
        } else {
            System.out.println("Instrumentos financieros registrados:");
            instrumentos.forEach(instrumento -> System.out.println(instrumento.getNombre()));
        }
    }

    public boolean eliminarInstrumentoPorNombre(String nombreInstrumento) {
        return instrumentos.removeIf(instrumento -> instrumento.getNombre().equalsIgnoreCase(nombreInstrumento));
    }

}

