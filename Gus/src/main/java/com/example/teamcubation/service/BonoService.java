package com.example.teamcubation.service;

import com.example.teamcubation.exceptions.InstrumentoDuplicadoException;
import com.example.teamcubation.exceptions.InstrumentoNoEncontradoException;
import com.example.teamcubation.model.Bono;

import java.util.List;

public interface BonoService {
    Bono createBono(Bono nuevoBono) throws InstrumentoDuplicadoException;

    List<Bono> getAllBonos();

    Bono updateBono(Bono bonoActualizado) throws InstrumentoNoEncontradoException, InstrumentoDuplicadoException;

    void deleteBono(long id) throws InstrumentoNoEncontradoException;

}
