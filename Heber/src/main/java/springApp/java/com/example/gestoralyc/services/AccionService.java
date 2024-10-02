package springApp.java.com.example.gestoralyc.services;

import springApp.java.com.example.gestoralyc.exceptions.InstrumentoDuplicadoException;
import springApp.java.com.example.gestoralyc.exceptions.InstrumentoNoEncontradoException;
import springApp.java.com.example.gestoralyc.exceptions.InvalidInstrumentoDataException;
import springApp.java.com.example.gestoralyc.models.AccionModel;

import java.util.List;

public interface AccionService {
    List<AccionModel> obtenerAcciones();

    AccionModel agregarAccion(AccionModel accion) throws InstrumentoDuplicadoException, InvalidInstrumentoDataException;

    AccionModel obtenerAccionPorId(Long id) throws InstrumentoNoEncontradoException;

    void eliminarAccionPorId(Long id) throws InstrumentoNoEncontradoException;

    AccionModel editarAccion(Long id, AccionModel accionModel) throws InstrumentoNoEncontradoException, InstrumentoDuplicadoException;

    AccionModel getAccionPorNombre(String nombre);
}
