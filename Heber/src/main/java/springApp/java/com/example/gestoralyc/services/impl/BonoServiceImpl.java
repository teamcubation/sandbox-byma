package springApp.java.com.example.gestoralyc.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import springApp.java.com.example.gestoralyc.exceptions.InstrumentoDuplicadoException;
import springApp.java.com.example.gestoralyc.exceptions.InstrumentoNoEncontradoException;
import springApp.java.com.example.gestoralyc.exceptions.InvalidInstrumentoDataException;
import springApp.java.com.example.gestoralyc.models.AccionModel;
import springApp.java.com.example.gestoralyc.models.BonoModel;
import springApp.java.com.example.gestoralyc.repositories.BonoRepository;
import springApp.java.com.example.gestoralyc.services.AccionService;
import springApp.java.com.example.gestoralyc.services.BonoService;

import java.util.List;

@Service
public class BonoServiceImpl implements BonoService {

    @Autowired
    private BonoRepository bonoRepository;

    @Autowired
    @Lazy
    private AccionService accionService;

    @Override
    public List<BonoModel> obtenerBonos() {
        return bonoRepository.findAll();
    }

    @Override
    public BonoModel agregarBono(BonoModel bono) throws InstrumentoDuplicadoException, InvalidInstrumentoDataException {
        //TODO: Validar que los datos de la acción sean correctos
        if (bonoRepository.existsByNombreIgnoreCase(bono.getNombre())) {
            throw new InstrumentoDuplicadoException("El bono con nombre " + bono.getNombre() + " ya existe");
        }
        if (bono.getNombre().isEmpty() || bono.getNombre().isBlank()) {
            throw new InvalidInstrumentoDataException("El nombre del bono no puede estar vacío");
        }

        AccionModel accionObtenida = accionService.getAccionPorNombre(bono.getNombre());


        return bonoRepository.save(bono);
    }


    @Override
    public BonoModel obtenerBonoPorId(Long id) throws InstrumentoNoEncontradoException {
        return bonoRepository.findById(id).orElseThrow(() -> new InstrumentoNoEncontradoException("El bono con id " + id + " no fue encontrado"));
    }

    @Override
    public void eliminarBonoPorId(Long id) throws InstrumentoNoEncontradoException {
        if (!bonoRepository.existsById(id)) {
            throw new InstrumentoNoEncontradoException("El bono con id " + id + " no fue encontrado");
        }
        bonoRepository.deleteById(id);
    }

    @Override
    public BonoModel editarBono(Long id, BonoModel bonoModel) throws InstrumentoNoEncontradoException, InstrumentoDuplicadoException {
        // Busca el bono existente por el ID
        BonoModel bonoExistente = bonoRepository.findById(id)
                .orElseThrow(() -> new InstrumentoNoEncontradoException("El bono con id " + id + " no fue encontrado"));

        // Verifica si hay un bono con el mismo nombre, pero que no sea el bono actual
        if (bonoRepository.existsByNombreIgnoreCase(bonoModel.getNombre()) &&
                !bonoModel.getNombre().equalsIgnoreCase(bonoExistente.getNombre())) {
            throw new InstrumentoDuplicadoException("El bono con nombre " + bonoModel.getNombre() + " ya existe");
        }

        // Actualiza los campos del bono existente con los nuevos valores
        bonoExistente.setNombre(bonoModel.getNombre());
        bonoExistente.setPrecio(bonoModel.getPrecio());
        bonoExistente.setTasaInteres(bonoModel.getTasaInteres());

        // Guarda el bono actualizado
        return bonoRepository.save(bonoExistente);
    }

    @Override
    public BonoModel getBonoPorNombre(String nombre) {
        return bonoRepository.findByNombre(nombre);
    }


}