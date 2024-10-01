package springApp.java.com.example.gestoralyc.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import springApp.java.com.example.gestoralyc.exceptions.InstrumentoDuplicadoException;
import springApp.java.com.example.gestoralyc.exceptions.InstrumentoNoEncontradoException;
import springApp.java.com.example.gestoralyc.exceptions.InvalidInstrumentoDataException;
import springApp.java.com.example.gestoralyc.models.BonoModel;
import springApp.java.com.example.gestoralyc.repositories.BonoRepository;
import springApp.java.com.example.gestoralyc.services.BonoService;

import java.util.List;

@Service
@AllArgsConstructor
public class BonoServiceImpl implements BonoService {

    private final BonoRepository bonoRepository;

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
        BonoModel bono = bonoRepository.findById(id).orElseThrow(() -> new InstrumentoNoEncontradoException("El bono con id " + id + " no fue encontrado"));
        if (bonoRepository.existsByNombreIgnoreCase(bonoModel.getNombre()) && !bono.getNombre().equalsIgnoreCase(bonoModel.getNombre())) {
            throw new InstrumentoDuplicadoException("El bono con nombre " + bonoModel.getNombre() + " ya existe");
        }
        return bonoRepository.save(bono);
    }


}