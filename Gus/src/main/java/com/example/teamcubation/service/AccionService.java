package com.example.teamcubation.service;

import com.example.teamcubation.exceptions.InstrumentoDuplicadoException;
import com.example.teamcubation.exceptions.InstrumentoNoEncontradoException;
import com.example.teamcubation.model.Accion;

import java.util.List;

public interface AccionService {
    Accion createAccion(Accion nuevaAccion) throws InstrumentoDuplicadoException;

    List<Accion> getAllAcciones();

    Accion updateAccion(Accion accion) throws InstrumentoNoEncontradoException, InstrumentoDuplicadoException;

    void deleteAccion(long id) throws InstrumentoNoEncontradoException;
}

