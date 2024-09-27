package springBootProject.java.com.example.proyectoSpringBoot.service;

import springBootProject.java.com.example.proyectoSpringBoot.dto.InstrumentoFinancieroDTO;
import springBootProject.java.com.example.proyectoSpringBoot.excepciones.customExcepcions.InstrumentoDuplicadoException;
import springBootProject.java.com.example.proyectoSpringBoot.excepciones.customExcepcions.InstrumentoNoEncontradoException;
import springBootProject.java.com.example.proyectoSpringBoot.excepciones.customExcepcions.OpcionInvalidaException;
import springBootProject.java.com.example.proyectoSpringBoot.model.InstrumentoFinanciero;

import java.util.List;

public interface InstrumentoFinancieroService {

    List<InstrumentoFinanciero> consultarTodos();

    InstrumentoFinanciero consultar(String nombre) throws InstrumentoNoEncontradoException;

    InstrumentoFinanciero registrar(InstrumentoFinancieroDTO instrumentoFinancieroDTO) throws InstrumentoDuplicadoException, OpcionInvalidaException;

    void eliminar(String nombre) throws InstrumentoNoEncontradoException;

    InstrumentoFinanciero editar(String instrumentoAEditar, InstrumentoFinancieroDTO instrumentoDTO) throws InstrumentoNoEncontradoException, OpcionInvalidaException;
}
