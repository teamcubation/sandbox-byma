package repositories;

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
}

