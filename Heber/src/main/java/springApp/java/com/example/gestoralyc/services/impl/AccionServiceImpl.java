package springApp.java.com.example.gestoralyc.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import springApp.java.com.example.gestoralyc.exceptions.InstrumentoDuplicadoException;
import springApp.java.com.example.gestoralyc.exceptions.InstrumentoNoEncontradoException;
import springApp.java.com.example.gestoralyc.exceptions.InvalidInstrumentoDataException;
import springApp.java.com.example.gestoralyc.models.AccionModel;
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

    public void validarSiExisteAccionPorNombre(String nombre) throws InstrumentoDuplicadoException {
        if (accionRepository.existsByNombreIgnoreCase(nombre)) {
            throw new InstrumentoDuplicadoException(nombre);
        }
    }

    public void validarSiExisteAccionPorId(Long id) throws InstrumentoNoEncontradoException {
        if (!accionRepository.existsById(id)) {
            throw new InstrumentoNoEncontradoException(id);
        }
    }

    @Override
    public List<AccionModel> obtenerAcciones() {
        return accionRepository.findAll();
    }

    @Override
    public AccionModel agregarAccion(AccionModel accion) throws InstrumentoDuplicadoException, InvalidInstrumentoDataException {
        validarSiExisteAccionPorNombre(accion.getNombre());
        bonoService.validarSiExisteBonoPorNombre(accion.getNombre());

        return accionRepository.save(accion);
    }

    @Override
    public AccionModel obtenerAccionPorId(Long id) throws InstrumentoNoEncontradoException {
        validarSiExisteAccionPorId(id);
        return accionRepository.findById(id).get();
    }

    @Override
    public void eliminarAccionPorId(Long id) throws InstrumentoNoEncontradoException {
        validarSiExisteAccionPorId(id);
        accionRepository.deleteById(id);
    }

    @Override
    public AccionModel editarAccion(Long id, AccionModel accionModel) throws InstrumentoNoEncontradoException, InstrumentoDuplicadoException {
        AccionModel accionExistente = obtenerAccionPorId(id);
        if (!accionExistente.getNombre().equalsIgnoreCase(accionModel.getNombre())) {
            validarSiExisteAccionPorNombre(accionModel.getNombre());
            bonoService.validarSiExisteBonoPorNombre(accionModel.getNombre());
        }

        if (accionModel.getPrecio() == null || accionModel.getPrecio() <= 0) {
            accionModel.setPrecio(accionExistente.getPrecio());
        }

        if (accionModel.getDividendo() == null || accionModel.getDividendo() <= 0) {
            accionModel.setDividendo(accionExistente.getDividendo());
        }

        if (accionModel.getNombre() == null || accionModel.getNombre().trim().isEmpty()) {
            accionModel.setNombre(accionExistente.getNombre());
        }
        
        accionExistente.setNombre(accionModel.getNombre());
        accionExistente.setPrecio(accionModel.getPrecio());
        accionExistente.setDividendo(accionModel.getDividendo());

        return accionRepository.save(accionExistente);
    }

    @Override
    public AccionModel getAccionPorNombre(String nombre) {
        return accionRepository.findByNombre(nombre);
    }


}
