package springApp.java.com.example.gestoralyc.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import springApp.java.com.example.gestoralyc.exceptions.InstrumentoDuplicadoException;
import springApp.java.com.example.gestoralyc.exceptions.InstrumentoNoEncontradoException;
import springApp.java.com.example.gestoralyc.exceptions.InvalidInstrumentoDataException;
import springApp.java.com.example.gestoralyc.models.AccionModel;
import springApp.java.com.example.gestoralyc.models.BonoModel;
import springApp.java.com.example.gestoralyc.repositories.AccionRepository;
import springApp.java.com.example.gestoralyc.services.AccionService;
import springApp.java.com.example.gestoralyc.services.BonoService;

import java.util.List;

@Service
public class AccionServiceImpl implements AccionService {

    @Autowired
    private AccionRepository accionRepository;

    @Autowired
    @Lazy
    private BonoService bonoService;

    @Override
    public List<AccionModel> obtenerAcciones() {
        return accionRepository.findAll();
    }

    @Override
    public AccionModel agregarAccion(AccionModel accion) throws InstrumentoDuplicadoException, InvalidInstrumentoDataException {
        //TODO: Validar que los datos de la acción sean correctos
        //el nombre de la accion es unico
        if (accionRepository.existsByNombreIgnoreCase(accion.getNombre())) {
            throw new InstrumentoDuplicadoException("La acción con nombre " + accion.getNombre() + " ya existe");
        }
        if (accion.getNombre().isEmpty() || accion.getNombre().isBlank()) {
            throw new InvalidInstrumentoDataException("El nombre de la acción no puede estar vacío");
        }

        BonoModel bonoObtenido = bonoService.getBonoPorNombre(accion.getNombre());

        if (bonoObtenido != null) {
            throw new InstrumentoDuplicadoException("El bono con nombre " + accion.getNombre() + " ya existe");
        }


        return accionRepository.save(accion);
    }

    @Override
    public AccionModel obtenerAccionPorId(Long id) throws InstrumentoNoEncontradoException {
        return accionRepository.findById(id).orElseThrow(() -> new InstrumentoNoEncontradoException("La acción con id " + id + " no fue encontrada"));
    }

    @Override
    public void eliminarAccionPorId(Long id) throws InstrumentoNoEncontradoException {
        if (!accionRepository.existsById(id)) {
            throw new InstrumentoNoEncontradoException("La acción con id " + id + " no fue encontrada");
        }
        accionRepository.deleteById(id);
    }

    @Override
    public AccionModel editarAccion(Long id, AccionModel accionModel) throws InstrumentoNoEncontradoException, InstrumentoDuplicadoException {
        // Busca la acción existente por el ID
        AccionModel accionExistente = accionRepository.findById(id)
                .orElseThrow(() -> new InstrumentoNoEncontradoException("La acción con id " + id + " no fue encontrada"));

        // Verifica si hay una acción con el mismo nombre, pero que no sea la actual
        if (accionRepository.existsByNombreIgnoreCase(accionModel.getNombre()) &&
                !accionModel.getNombre().equalsIgnoreCase(accionExistente.getNombre())) {
            throw new InstrumentoDuplicadoException("La acción con nombre " + accionModel.getNombre() + " ya existe");
        }

        BonoModel bonoObtenido = bonoService.getBonoPorNombre(accionModel.getNombre());

        if (bonoObtenido != null) {
            throw new InstrumentoDuplicadoException("El bono con nombre " + accionModel.getNombre() + " ya existe");
        }

        // Actualiza los campos de la acción existente con los nuevos valores
        accionExistente.setNombre(accionModel.getNombre());
        accionExistente.setPrecio(accionModel.getPrecio());
        accionExistente.setDividendo(accionModel.getDividendo());  // Actualiza cualquier campo específico de la acción

        // Guarda la acción actualizada
        return accionRepository.save(accionExistente);
    }

    @Override
    public AccionModel getAccionPorNombre(String nombre) {
        return accionRepository.findByNombre(nombre);
    }


}
