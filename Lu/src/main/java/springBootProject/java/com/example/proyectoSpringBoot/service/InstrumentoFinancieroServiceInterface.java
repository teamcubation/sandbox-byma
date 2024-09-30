package com.example.proyectoSpringBoot.service;

import com.example.proyectoSpringBoot.dto.InstrumentoFinancieroDTO;
import com.example.proyectoSpringBoot.model.InstrumentoFinanciero;

import java.util.List;

public interface InstrumentoFinancieroServiceInterface {

    List<InstrumentoFinancieroDTO> consultarTodos();

    InstrumentoFinancieroDTO consultar(Long id) throws Exception;

    InstrumentoFinancieroDTO registrar(InstrumentoFinancieroDTO instrumentoFinancieroDTO) throws Exception;

    void eliminar(Long id) throws Exception;

    InstrumentoFinancieroDTO editar(Long id, InstrumentoFinancieroDTO instrumentoDTO) throws Exception;
}
