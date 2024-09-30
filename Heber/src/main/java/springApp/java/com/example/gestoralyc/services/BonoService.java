package springApp.java.com.example.gestoralyc.services;

import springApp.java.com.example.gestoralyc.exceptions.InstrumentoDuplicadoException;
import springApp.java.com.example.gestoralyc.models.BonoModel;

import java.util.List;

public interface BonoService {
    List<BonoModel> obtenerBonos();
    BonoModel agregarBono(BonoModel bono) throws InstrumentoDuplicadoException;
}
