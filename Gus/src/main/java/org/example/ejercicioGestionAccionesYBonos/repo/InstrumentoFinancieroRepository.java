package org.example.ejercicioGestionAccionesYBonos.repo;

import org.example.ejercicioGestionAccionesYBonos.modelo.InstrumentoFinanciero;

import java.util.List;
import java.util.Optional;

public interface InstrumentoFinancieroRepository {
    List<InstrumentoFinanciero> listarBonosYAcciones();

    List<InstrumentoFinanciero> consultarBonos();

    List<InstrumentoFinanciero> consultarAcciones();

    Optional<InstrumentoFinanciero> listarInstrumentoPorNombre(String nombreInstrumento);

    void registrarNuevoInstrumento(InstrumentoFinanciero nuevoInstrumento);

    void editarInstrumento(String nuevoNombre, double nuevoPrecio, String nombreInstrumento);

    void editarNombreInstrumento(String nuevoNombre, String nombreInstrumento);

    void editarPrecioInstrumento(double nuevoPrecio, String nombreInstrumento);

    void eliminarInstrumento(String nombreInstrumento);

}
