package springApp.java.com.example.gestoralyc.services;

import springApp.java.com.example.gestoralyc.exceptions.InstrumentoDuplicadoException;
import springApp.java.com.example.gestoralyc.models.AccionModel;

import java.util.List;

public interface AccionService {
    List<AccionModel> obtenerAcciones();
    AccionModel agregarAccion(AccionModel accion) throws InstrumentoDuplicadoException;
}
