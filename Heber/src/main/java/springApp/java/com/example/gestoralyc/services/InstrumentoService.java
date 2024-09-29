package springApp.java.com.example.gestoralyc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springApp.java.com.example.gestoralyc.exceptions.InstrumentoDuplicadoException;
import springApp.java.com.example.gestoralyc.exceptions.InstrumentoNoEncontradoException;
import springApp.java.com.example.gestoralyc.exceptions.InvalidInstrumentoDataException;
import springApp.java.com.example.gestoralyc.models.InstrumentoFinancieroModel;
import springApp.java.com.example.gestoralyc.repositories.InstrumentoFinancieroRepository;
import springApp.java.com.example.gestoralyc.utils.ValidationUtils;

import java.util.List;
import java.util.Optional;

@Service
public class InstrumentoService {

    @Autowired
    InstrumentoFinancieroRepository instrumentoRepository;

    public InstrumentoFinancieroModel agregarInstrumento(InstrumentoFinancieroModel instrumento) throws InstrumentoDuplicadoException, InvalidInstrumentoDataException {
        if (instrumentoRepository.existsById(instrumento.getId())) {
            throw new InstrumentoDuplicadoException("El instrumento con id " + instrumento.getId() + " ya existe");
        }
        if (!ValidationUtils.isValidInstrumento(instrumento)) {
            throw new InvalidInstrumentoDataException("Los datos del instrumento no son válidos");
        }
        return instrumentoRepository.save(instrumento);
    }

    public List<InstrumentoFinancieroModel> obtenerInstrumentos() {
        return instrumentoRepository.findAll();
    }


}
