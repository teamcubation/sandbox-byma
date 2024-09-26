package springApp.java.com.example.demo.repositories;

import org.springframework.stereotype.Repository;
import springApp.java.com.example.demo.exceptions.InstrumentoNoEncontradoException;
import springApp.java.com.example.demo.models.InstrumentoFinancieroModel;
import springApp.java.com.example.demo.utils.ValidationUtils;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InstrumentoRepository {

    private List<InstrumentoFinancieroModel> instrumentosFinancieros = new ArrayList<>();
    private Long currentId = 1L; // Variable para gestionar el ID autoincremental

    public void agregarInstrumento(InstrumentoFinancieroModel instrumento) {
        ValidationUtils.validarNoNulo(instrumento, "El instrumento no puede ser nulo.");
        instrumento.setId(generarNuevoId());
        instrumentosFinancieros.add(instrumento);
    }

    private Long generarNuevoId() {
        return currentId++;
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

    public boolean existeInstrumento(String nombre) {
        return instrumentosFinancieros.stream().anyMatch(i -> i.getNombre().equals(nombre));
    }

    public void editarInstrumento(Long id, InstrumentoFinancieroModel nuevoInstrumento) {
        InstrumentoFinancieroModel instrumentoExistente = obtenerInstrumento(id)
                .orElseThrow(() -> new InstrumentoNoEncontradoException("Instrumento no encontrado."));
        instrumentosFinancieros.remove(instrumentoExistente);
        instrumentosFinancieros.add(nuevoInstrumento);
    }
}

