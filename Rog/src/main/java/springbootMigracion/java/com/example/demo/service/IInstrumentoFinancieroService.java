package springbootMigracion.java.com.example.demo.service;

import springbootMigracion.java.com.example.demo.dto.InstrumentoDTO;
import springbootMigracion.java.com.example.demo.model.InstrumentoFinanciero;

import java.util.List;

public interface IInstrumentoFinancieroService {
    InstrumentoFinanciero registrarInstrumento(InstrumentoDTO instrumentoDTO) throws Exception;
    InstrumentoFinanciero buscarInstrumentoPorId(Long id) throws Exception;
    List<InstrumentoFinanciero> listarTodosLosInstrumentos();
    List<InstrumentoFinanciero> listarInstrumentosPorNombre(String nombre);
    void eliminarInstrumento(Long id) throws Exception;
    InstrumentoFinanciero editarInstrumento(Long id, InstrumentoDTO instrumentoDTO) throws Exception;
    void suscribirInversor(String nombreInstrumento, String nombreInversor) throws Exception;
}
