package springApp.java.com.example.gestoralyc.services;

import springApp.java.com.example.gestoralyc.exceptions.InstrumentoDuplicadoException;
import springApp.java.com.example.gestoralyc.exceptions.InstrumentoNoEncontradoException;
import springApp.java.com.example.gestoralyc.exceptions.InvalidInstrumentoDataException;
import springApp.java.com.example.gestoralyc.models.BonoModel;

import java.util.List;

public interface BonoService {
    List<BonoModel> obtenerBonos();

    BonoModel agregarBono(BonoModel bono) throws InstrumentoDuplicadoException, InvalidInstrumentoDataException;

    BonoModel obtenerBonoPorId(Long id) throws InstrumentoNoEncontradoException;

    void eliminarBonoPorId(Long id) throws InstrumentoNoEncontradoException;

    BonoModel editarBono(Long id, BonoModel bonoModel) throws InstrumentoNoEncontradoException, InstrumentoDuplicadoException;

    BonoModel getBonoPorNombre(String nombre);

    void validarSiExisteBonoPorNombre(String nombre);
}
