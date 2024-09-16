package org.example.ejercicioGestionAccionesYBonos.repo;

import org.example.ejercicioGestionAccionesYBonos.modelo.InstrumentoFinanciero;
import org.example.ejercicioGestionAccionesYBonos.util.InstrumentoDuplicadoException;
import org.example.ejercicioGestionAccionesYBonos.util.InstrumentoNoEncontradoException;

import java.util.List;
import java.util.Optional;

public interface InstrumentoFinancieroRepository {
    List<InstrumentoFinanciero> listarBonosYAcciones();
    List<InstrumentoFinanciero> consultarBonos();
    List<InstrumentoFinanciero> consultarAcciones();

    Optional<InstrumentoFinanciero> listarInstrumentoPorNombre(String nombreInstrumento);

    void registrarNuevoInstrumento(InstrumentoFinanciero nuevoInstrumento) throws InstrumentoDuplicadoException;

    void editarInstrumento(String nuevoNombre, double nuevoPrecio, String nombreInstrumento) throws InstrumentoNoEncontradoException;
    void editarNombreInstrumento(String nuevoNombre, String nombreInstrumento) throws InstrumentoNoEncontradoException;
    void editarPrecioInstrumento(double nuevoPrecio, String nombreInstrumento) throws InstrumentoNoEncontradoException;

    void eliminarInstrumento(String nombreInstrumento) throws InstrumentoNoEncontradoException;

}
