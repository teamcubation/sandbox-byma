package springApp.java.com.example.gestoralyc.repositories;

import org.springframework.stereotype.Repository;
import springApp.java.com.example.gestoralyc.exceptions.InstrumentoNoEncontradoException;
import springApp.java.com.example.gestoralyc.exceptions.InvalidInstrumentoDataException;
import springApp.java.com.example.gestoralyc.models.InstrumentoFinancieroModel;
import springApp.java.com.example.gestoralyc.utils.ValidationUtils;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InstrumentoRepository {

    private List<InstrumentoFinancieroModel> instrumentosFinancieros = new ArrayList<>();
    private Long currentId = 1L; // Variable para gestionar el ID autoincremental

    public InstrumentoFinancieroModel agregarInstrumento(InstrumentoFinancieroModel instrumento) throws InvalidInstrumentoDataException {
        ValidationUtils.validarNoNulo(instrumento, "El instrumento no puede ser nulo.");
        generarNuevoId(instrumento);
        instrumentosFinancieros.add(instrumento);
        return instrumento;
    }

    private void generarNuevoId(InstrumentoFinancieroModel instrumento) {
        instrumento.setId(currentId);
        currentId++;
    }

    public List<InstrumentoFinancieroModel> obtenerInstrumentos() {
        return instrumentosFinancieros;
    }

    public void eliminarInstrumento(Long id) {
        instrumentosFinancieros.removeIf(i -> i.getId().equals(id));
    }

    public Optional<InstrumentoFinancieroModel> obtenerInstrumento(Long id) {
        return instrumentosFinancieros.stream()
                .filter(instrumento -> instrumento.getId().equals(id))
                .findFirst();
    }

    public boolean existeInstrumento(String nombre) {
        return instrumentosFinancieros.stream().anyMatch(i -> i.getNombre().equals(nombre));
    }

    public Optional<InstrumentoFinancieroModel> editarInstrumento(Long id, InstrumentoFinancieroModel nuevoInstrumento) {
        Optional<InstrumentoFinancieroModel> instrumentoExistenteOpt = obtenerInstrumento(id);

        if (instrumentoExistenteOpt.isPresent()) {
            InstrumentoFinancieroModel instrumentoExistente = instrumentoExistenteOpt.get();
            instrumentosFinancieros.remove(instrumentoExistente);
            nuevoInstrumento.setId(instrumentoExistente.getId());
            instrumentosFinancieros.add(nuevoInstrumento);

            return Optional.of(nuevoInstrumento);
        }

        return Optional.empty();
    }


}

