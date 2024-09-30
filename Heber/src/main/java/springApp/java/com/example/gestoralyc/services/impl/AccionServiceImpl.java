package springApp.java.com.example.gestoralyc.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springApp.java.com.example.gestoralyc.exceptions.InstrumentoDuplicadoException;
import springApp.java.com.example.gestoralyc.models.AccionModel;
import springApp.java.com.example.gestoralyc.repositories.AccionRepository;
import springApp.java.com.example.gestoralyc.services.AccionService;

import java.util.List;

@Service
public class AccionServiceImpl implements AccionService {

    @Autowired
    AccionRepository accionRepository;

    @Override
    public List<AccionModel> obtenerAcciones() {
        return accionRepository.findAll();
    }

    @Override
    public AccionModel agregarAccion(AccionModel accion) throws InstrumentoDuplicadoException {
        //TODO: Validar que los datos de la acción sean correctos
        //el nombre de la accion es unico
        if (accionRepository.existsByNombre(accion.getNombre())) {
            throw new InstrumentoDuplicadoException("La acción con nombre " + accion.getNombre() + " ya existe");
        }
        return accionRepository.save(accion);
    }


}
