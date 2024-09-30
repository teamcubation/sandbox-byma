package com.example.teamcubation.service.impl;

import com.example.teamcubation.exceptions.InstrumentoDuplicadoException;
import com.example.teamcubation.exceptions.InstrumentoNoEncontradoException;
import com.example.teamcubation.model.Accion;
import com.example.teamcubation.repository.interfaces.AccionRepository;
import com.example.teamcubation.service.AccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccionServiceImpl implements AccionService {

    private final AccionRepository accionRepository;

    @Autowired
    public AccionServiceImpl(AccionRepository accionRepository) {
        this.accionRepository = accionRepository;
    }

    @Override
    public Accion createAccion(Accion nuevaAccion) throws InstrumentoDuplicadoException {

        if (this.accionEsDuplicado(nuevaAccion.getNombre())) {
            throw new InstrumentoDuplicadoException("Error: Ya existe un instrumento con el mismo nombre");
        }

        return this.accionRepository.save(nuevaAccion);
    }

    @Override
    public List<Accion> getAllAcciones() {
        return this.accionRepository.findAll();
    }

    @Override
    public Accion updateAccion(Accion accion) throws InstrumentoNoEncontradoException, InstrumentoDuplicadoException {

        if (this.accionEsInexistente(accion.getId())) {
            throw new InstrumentoNoEncontradoException("Error: El instrumento no existe");
        }

        if (this.accionEsDuplicado(accion.getNombre())) {
            throw new InstrumentoDuplicadoException("Error: Ya existe un instrumento con el mismo nombre");
        }

        return this.accionRepository.save(accion);
    }

    @Override
    public void deleteAccion(long id) throws InstrumentoNoEncontradoException {

        if (this.accionEsInexistente(id)) {
            throw new InstrumentoNoEncontradoException("Error: El instrumento no existe");
        }

        this.accionRepository.deleteById(id);
    }


    //Validaciones
    private boolean accionEsInexistente(long id) {
        return !this.accionRepository
                .existsById(id);
    }

    private boolean accionEsDuplicado(String nombre) {
        return this.accionRepository
                .findAll()
                .stream()
                .anyMatch(accion -> accion.getNombre().equalsIgnoreCase(nombre));
    }
}
