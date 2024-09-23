package springbootMigracion.java.com.example.demo.repository;

import springbootMigracion.java.com.example.demo.model.InstrumentoFinanciero;


import java.util.List;
import java.util.Optional;

public interface IInstrumentoFinancieroRepository {
    void agregarInstrumento(InstrumentoFinanciero instrumento);
    List<InstrumentoFinanciero> listarTodosLosInstrumentos();
    Optional<InstrumentoFinanciero> buscarInstrumentoPorNombre(String nombre);
    void eliminarInstrumento(String nombre);
    void editarInstrumento(String nombre, InstrumentoFinanciero nuevoInstrumento);
}
