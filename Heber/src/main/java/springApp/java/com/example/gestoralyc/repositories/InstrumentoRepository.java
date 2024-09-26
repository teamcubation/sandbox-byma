package springApp.java.com.example.gestoralyc.repositories;

import org.springframework.stereotype.Repository;
import springApp.java.com.example.gestoralyc.exceptions.InstrumentoNoEncontradoException;
import springApp.java.com.example.gestoralyc.models.InstrumentoFinancieroModel;
import springApp.java.com.example.gestoralyc.utils.ValidationUtils;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InstrumentoRepository {

    private List<InstrumentoFinancieroModel> instrumentosFinancieros = new ArrayList<>();
    private Long currentId = 1L; // Variable para gestionar el ID autoincremental

    public InstrumentoFinancieroModel agregarInstrumento(InstrumentoFinancieroModel instrumento) {
        ValidationUtils.validarNoNulo(instrumento, "El instrumento no puede ser nulo.");
        instrumento.setId(generarNuevoId());
        instrumentosFinancieros.add(instrumento);
        return instrumento;
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

    public InstrumentoFinancieroModel obtenerInstrumento(Long id) {
        return instrumentosFinancieros.stream().filter(i -> i.getId().equals(id)).findFirst().orElse(null);
    }

    public boolean existeInstrumento(String nombre) {
        return instrumentosFinancieros.stream().anyMatch(i -> i.getNombre().equals(nombre));
    }

    public InstrumentoFinancieroModel editarInstrumento(Long id, InstrumentoFinancieroModel nuevoInstrumento) {
        InstrumentoFinancieroModel instrumentoExistente = obtenerInstrumento(id);
        ValidationUtils.validarNoNulo(instrumentoExistente, "El instrumento no existe.");
        instrumentosFinancieros.remove(instrumentoExistente);
        nuevoInstrumento.setId(instrumentoExistente.getId()); //le paso el id del instrumentoEliminado al nuevo
        instrumentosFinancieros.add(nuevoInstrumento);
        return nuevoInstrumento;
    }


}

