package com.example.teamcubation.service.impl;

import com.example.teamcubation.exceptions.InstrumentoDuplicadoException;
import com.example.teamcubation.exceptions.InstrumentoNoEncontradoException;
import com.example.teamcubation.model.Bono;
import com.example.teamcubation.repository.interfaces.BonoRepository;
import com.example.teamcubation.service.BonoService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BonoServiceImpl implements BonoService {


    private final BonoRepository bonoRepository;


    private final AccionServiceImpl accionService;


    public BonoServiceImpl(BonoRepository bonoRepository, @Lazy AccionServiceImpl accionService) {
        this.bonoRepository = bonoRepository;
        this.accionService = accionService;
    }


    @Override
    public Bono createBono(Bono nuevoBono) throws InstrumentoDuplicadoException {

        if (this.instrumentoEsDuplicado(nuevoBono.getNombre())) {
            throw new InstrumentoDuplicadoException("Error: Ya existe un bono con ese nombre!!!");
        }
        return this.bonoRepository.save(nuevoBono);
    }

    @Override
    public List<Bono> getAllBonos() {
        return this.bonoRepository.findAll();
    }

    @Override
    public Bono updateBono(Bono bonoActualizado) throws InstrumentoNoEncontradoException, InstrumentoDuplicadoException {

        if (this.bonoEsInexistente(bonoActualizado.getId())) {
            throw new InstrumentoNoEncontradoException("Error: El bono no existe");
        }

        if (this.instrumentoEsDuplicado(bonoActualizado.getNombre())) {
            throw new InstrumentoDuplicadoException("Error: Ya existe un bono con ese nombre");
        }

        return this.bonoRepository.save(bonoActualizado);
    }

    @Override
    public void deleteBono(long id) throws InstrumentoNoEncontradoException {

        if (bonoEsInexistente(id)) {
            throw new InstrumentoNoEncontradoException("Error: El bono con el id: " + id + " no existe");
        }

        this.bonoRepository.deleteById(id);
    }

    //Validaciones
    private boolean bonoEsInexistente(long id) {

        return !this.bonoRepository
                .existsById(id);
    }

    private boolean instrumentoEsDuplicado(String nombre) {
        return bonoEsDuplicado(nombre) || accionEsDuplicado(nombre);
    }

    public boolean bonoEsDuplicado(String nombre) {
        return this.bonoRepository
                .findAll()
                .stream()
                .anyMatch(bono -> bono.getNombre().equalsIgnoreCase(nombre)) || this.accionService.accionEsDuplicado(nombre);
    }

    private boolean accionEsDuplicado(String nombre) {
        return accionService.getAllAcciones()
                .stream()
                .anyMatch(accion -> accion.getNombre().equalsIgnoreCase(nombre));
    }
}
