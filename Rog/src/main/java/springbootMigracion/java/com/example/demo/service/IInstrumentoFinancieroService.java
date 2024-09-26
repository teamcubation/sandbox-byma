package springbootMigracion.java.com.example.demo.service;

import springbootMigracion.java.com.example.demo.model.InstrumentoFinanciero;

import java.util.List;
import java.util.Optional;

public interface IInstrumentoFinancieroService {
    void registrarInstrumento(InstrumentoFinanciero instrumento);
    void eliminarInstrumento(String nombre);
    List<InstrumentoFinanciero> listarTodosLosInstrumentos();
    Optional<InstrumentoFinanciero> buscarInstrumentoPorNombre(String nombre);
    void editarInstrumento(String nombre, InstrumentoFinanciero nuevoInstrumento);
    void suscribirInversor(String nombreInstrumento, String nombreInversor);
}
