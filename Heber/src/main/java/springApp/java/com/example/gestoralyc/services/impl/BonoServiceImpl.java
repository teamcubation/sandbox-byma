package springApp.java.com.example.gestoralyc.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import springApp.java.com.example.gestoralyc.exceptions.InstrumentoDuplicadoException;
import springApp.java.com.example.gestoralyc.exceptions.InstrumentoNoEncontradoException;
import springApp.java.com.example.gestoralyc.exceptions.InvalidInstrumentoDataException;
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

    public void validarSiExisteBonoPorNombre(String nombre) throws InstrumentoDuplicadoException {
        if (bonoRepository.existsByNombreIgnoreCase(nombre)) {
            throw new InstrumentoDuplicadoException(nombre);
        }
    }

    public void validarSiExisteBonoPorId(Long id) throws InstrumentoNoEncontradoException {
        if (!bonoRepository.existsById(id)) {
            throw new InstrumentoNoEncontradoException(id);
        }
    }

    @Override
    public List<BonoModel> obtenerBonos() {
        return bonoRepository.findAll();
    }

    @Override
    public BonoModel agregarBono(BonoModel bono) throws InstrumentoDuplicadoException, InvalidInstrumentoDataException {
        validarSiExisteBonoPorNombre(bono.getNombre());
        accionService.validarSiExisteAccionPorNombre(bono.getNombre());

        return bonoRepository.save(bono);
    }


    @Override
    public BonoModel obtenerBonoPorId(Long id) throws InstrumentoNoEncontradoException {
        validarSiExisteBonoPorId(id);
        return bonoRepository.findById(id).get();
    }

    @Override
    public void eliminarBonoPorId(Long id) throws InstrumentoNoEncontradoException {
        validarSiExisteBonoPorId(id);
        bonoRepository.deleteById(id);
    }

    @Override
    public BonoModel editarBono(Long id, BonoModel bonoModel) throws InstrumentoNoEncontradoException, InstrumentoDuplicadoException {
        BonoModel bonoExistente = obtenerBonoPorId(id);

        if (!bonoExistente.getNombre().equals(bonoModel.getNombre())) {
            validarSiExisteBonoPorNombre(bonoModel.getNombre());
            accionService.validarSiExisteAccionPorNombre(bonoModel.getNombre());
        }

        bonoExistente.setNombre(bonoModel.getNombre());
        bonoExistente.setPrecio(bonoModel.getPrecio());
        bonoExistente.setTasaInteres(bonoModel.getTasaInteres());

        return bonoRepository.save(bonoExistente);
    }

    @Override
    public BonoModel getBonoPorNombre(String nombre) {
        return bonoRepository.findByNombre(nombre);
    }


}