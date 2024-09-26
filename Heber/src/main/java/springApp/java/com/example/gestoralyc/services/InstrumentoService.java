package springApp.java.com.example.gestoralyc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springApp.java.com.example.gestoralyc.exceptions.InstrumentoDuplicadoException;
import springApp.java.com.example.gestoralyc.exceptions.InstrumentoNoEncontradoException;
import springApp.java.com.example.gestoralyc.models.InstrumentoFinancieroModel;
import springApp.java.com.example.gestoralyc.repositories.InstrumentoRepository;
import springApp.java.com.example.gestoralyc.utils.ValidationUtils;

import java.util.List;
import java.util.Optional;

@Service
public class InstrumentoService {

    @Autowired
    InstrumentoRepository instrumentoRepository;

    ///////////METODOS CRUD//////////
    public InstrumentoFinancieroModel agregarInstrumento(InstrumentoFinancieroModel instrumento) {
        // Validaciones antes de agregar el instrumento
        ValidationUtils.validarNoNulo(instrumento, "El instrumento no puede ser nulo");
        ValidationUtils.validarCadenaNoVacia(instrumento.getNombre(), "El nombre del instrumento no puede estar vacío");
        ValidationUtils.validarPrecioPositivo(instrumento.getPrecio(), "El precio del instrumento debe ser mayor que cero");

        // Verificar si el instrumento ya existe
        if (instrumentoRepository.existeInstrumento(instrumento.getNombre())) {
            throw new InstrumentoDuplicadoException("El instrumento " + instrumento.getNombre() + " ya existe");
        }

        return instrumentoRepository.agregarInstrumento(instrumento);
    }

    public List<InstrumentoFinancieroModel> obtenerInstrumentos() {
        return instrumentoRepository.obtenerInstrumentos();
    }

    public InstrumentoFinancieroModel obtenerInstrumento(Long id) {
        InstrumentoFinancieroModel instrumento = instrumentoRepository.obtenerInstrumento(id);
        ValidationUtils.validarNoNulo(instrumento, "El instrumento no existe");
        return instrumento;
    }

    public void eliminarInstrumentoPorId(Long id) {
        Optional<InstrumentoFinancieroModel> instrumento = instrumentoRepository.eliminarInstrumento(id);
        if (!instrumento.isPresent()) {
            throw new InstrumentoNoEncontradoException("El instrumento con id " + id + " no existe");
        }
    }

    public InstrumentoFinancieroModel editarInstrumento(Long id, InstrumentoFinancieroModel nuevoInstrumento) {
        // Validaciones antes de editar el instrumento
        ValidationUtils.validarNoNulo(nuevoInstrumento, "El instrumento no puede ser nulo");
        ValidationUtils.validarCadenaNoVacia(nuevoInstrumento.getNombre(), "El nombre del instrumento no puede estar vacío");
        ValidationUtils.validarPrecioPositivo(nuevoInstrumento.getPrecio(), "El precio del instrumento debe ser mayor que cero");

        instrumentoRepository.editarInstrumento(id, nuevoInstrumento);
        return nuevoInstrumento;
    }
}
