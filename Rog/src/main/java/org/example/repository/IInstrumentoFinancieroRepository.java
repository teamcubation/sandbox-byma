package org.example.repository;

import org.example.model.InstrumentoFinanciero;

import java.util.List;

public interface IInstrumentoFinancieroRepository {
    void agregarInstrumento(InstrumentoFinanciero instrumento);
    List<InstrumentoFinanciero> listarTodosLosInstrumentos();
    InstrumentoFinanciero buscarInstrumentoPorNombre(String nombre);
    void eliminarInstrumento(String nombre);
}
