package springApp.java.com.example.demo.repositories;

import org.springframework.stereotype.Repository;
import springApp.java.com.example.demo.exceptions.InstrumentoNoEncontradoException;
import springApp.java.com.example.demo.models.AccionModel;
import springApp.java.com.example.demo.models.BonoModel;
import springApp.java.com.example.demo.models.InstrumentoFinancieroModel;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InstrumentoRepository {

    private List<InstrumentoFinancieroModel> instrumentosFinancieros = new ArrayList<>();
    private Long currentId = 1L; // Variable para gestionar el ID autoincremental

    public String helloRepository() {
        return "Hello from Repository";
    }

    public AccionModel agregarAccion(AccionModel accion) {
        accion.setId(currentId++); // Asignar el ID y luego incrementarlo
        instrumentosFinancieros.add(accion);
        return accion;
    }

    public BonoModel agregarBono(BonoModel bono) {
        bono.setId(currentId++); // Asignar el ID y luego incrementarlo
        instrumentosFinancieros.add(bono);
        return bono;
    }

    public List<InstrumentoFinancieroModel> obtenerInstrumentos() {
        return instrumentosFinancieros;
    }

    public Optional<InstrumentoFinancieroModel> eliminarInstrumento(Long id) {
        Optional<InstrumentoFinancieroModel> instrumento = instrumentosFinancieros.stream().filter(i -> i.getId().equals(id)).findFirst();
        if (instrumento.isPresent()) {
            instrumentosFinancieros.remove(instrumento.get());
        }
        return instrumento;
    }

    public Optional<InstrumentoFinancieroModel> obtenerInstrumento(Long id) {
        return instrumentosFinancieros.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    public boolean verificarInstrumentoDuplicado(InstrumentoFinancieroModel instrumento) {
        return instrumentosFinancieros.stream().anyMatch(i -> i.getNombre().equals(instrumento.getNombre()));
    }

    public void editarInstrumento(Long id, InstrumentoFinancieroModel nuevoInstrumento) {
        InstrumentoFinancieroModel instrumentoExistente = obtenerInstrumento(id)
                .orElseThrow(() -> new InstrumentoNoEncontradoException("Instrumento no encontrado."));
        instrumentosFinancieros.remove(instrumentoExistente);
        instrumentosFinancieros.add(nuevoInstrumento);
    }
}

