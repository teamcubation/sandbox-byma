package springApp.java.com.example.gestoralyc.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springApp.java.com.example.gestoralyc.exceptions.InstrumentoDuplicadoException;
import springApp.java.com.example.gestoralyc.models.BonoModel;
import springApp.java.com.example.gestoralyc.repositories.BonoRepository;
import springApp.java.com.example.gestoralyc.services.BonoService;

import java.util.List;

@Service
public class BonoServiceImpl implements BonoService {

    @Autowired
    BonoRepository bonoRepository;

    @Override
    public List<BonoModel> obtenerBonos() {
        return bonoRepository.findAll();
    }

    @Override
    public BonoModel agregarBono(BonoModel bono) throws InstrumentoDuplicadoException {
        //TODO: Validar que los datos de la acción sean correctos
        if (bonoRepository.existsByNombre(bono.getNombre())) {
            throw new InstrumentoDuplicadoException("El bono con nombre " + bono.getNombre() + " ya existe");
        }
        return bonoRepository.save(bono);
    }


}