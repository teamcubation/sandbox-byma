package org.example.service;

import org.example.model.InstrumentoFinanciero;

import java.util.List;

public interface IInstrumentoFinancieroService {
    void registrarInstrumento(InstrumentoFinanciero instrumento);
    void eliminarInstrumento(String nombre);
    List<InstrumentoFinanciero> listarTodosLosInstrumentos();
    InstrumentoFinanciero buscarInstrumentoPorNombre(String nombre);
    void editarInstrumento(String nombre, String atributo, String nuevoValor);
}
