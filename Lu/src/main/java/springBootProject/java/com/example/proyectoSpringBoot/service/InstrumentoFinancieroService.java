package springBootProject.java.com.example.proyectoSpringBoot.service;

import springBootProject.java.com.example.proyectoSpringBoot.excepciones.InstrumentoDuplicadoException;
import springBootProject.java.com.example.proyectoSpringBoot.excepciones.InstrumentoNoEncontradoException;
import springBootProject.java.com.example.proyectoSpringBoot.model.InstrumentoFinanciero;

import java.util.List;

public interface InstrumentoFinancieroService {

    List<InstrumentoFinanciero> consultarTodos();

    InstrumentoFinanciero consultar(String nombre) throws InstrumentoNoEncontradoException;

    InstrumentoFinanciero registrar(String nombre, double precio, int tipo) throws InstrumentoDuplicadoException;

    InstrumentoFinanciero eliminar(String nombre) throws InstrumentoNoEncontradoException;

    InstrumentoFinanciero editar(String instrumentoAEditar, String nombreNuevo, double precio) throws InstrumentoNoEncontradoException;
}
