package com.example.proyectoSpringBoot.service;

import com.example.proyectoSpringBoot.dto.InstrumentoFinancieroDTO;
import com.example.proyectoSpringBoot.excepciones.customExcepcions.InstrumentoDuplicadoException;
import com.example.proyectoSpringBoot.excepciones.customExcepcions.InstrumentoNoEncontradoException;
import com.example.proyectoSpringBoot.excepciones.customExcepcions.OpcionInvalidaException;

import java.util.List;

public interface InstrumentoFinancieroServiceInterface {

    List<InstrumentoFinancieroDTO> consultarTodos();

    InstrumentoFinancieroDTO consultar(Long id) throws InstrumentoNoEncontradoException;

    InstrumentoFinancieroDTO registrar(InstrumentoFinancieroDTO instrumentoFinancieroDTO) throws InstrumentoDuplicadoException, OpcionInvalidaException;

    void eliminar(Long id) throws InstrumentoNoEncontradoException;

    InstrumentoFinancieroDTO editar(Long id, InstrumentoFinancieroDTO instrumentoDTO) throws InstrumentoNoEncontradoException, OpcionInvalidaException;
}
