package springApp.java.com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springApp.java.com.example.demo.exceptions.InstrumentoDuplicadoException;
import springApp.java.com.example.demo.exceptions.InstrumentoNoEncontradoException;
import springApp.java.com.example.demo.models.AccionModel;
import springApp.java.com.example.demo.models.BonoModel;
import springApp.java.com.example.demo.models.InstrumentoFinancieroModel;
import springApp.java.com.example.demo.repositories.InstrumentoRepository;

import java.util.List;
import java.util.Optional;


@Service
public class InstrumentoService {

    @Autowired
    InstrumentoRepository instrumentoRepository;

    public String helloService() {
        return "Hello from Service";
    }

    public String helloRepository() {
        return instrumentoRepository.helloRepository();
    }

    ///////////METODOS CRUD//////////
    public AccionModel agregarAccion(AccionModel accion) {
        if (instrumentoRepository.verificarInstrumentoDuplicado(accion)) {
            throw new InstrumentoDuplicadoException("El instrumento con nombre " + accion.getNombre() + " ya existe");
        }
        return instrumentoRepository.agregarAccion(accion);
    }

    public BonoModel agregarBono(BonoModel bono) {
        if (instrumentoRepository.verificarInstrumentoDuplicado(bono)) {
            throw new InstrumentoDuplicadoException("El instrumento con nombre " + bono.getNombre() + " ya existe");
        }
        return instrumentoRepository.agregarBono(bono);
    }

    public List<InstrumentoFinancieroModel> obtenerInstrumentos() {
        return instrumentoRepository.obtenerInstrumentos();
    }


    public Optional<InstrumentoFinancieroModel> eliminarInstrumento(Long id) {
        return instrumentoRepository.eliminarInstrumento(id);
    }

    public Optional<InstrumentoFinancieroModel> obtenerInstrumento(Long id) {
        Optional<InstrumentoFinancieroModel> instrumento = instrumentoRepository.obtenerInstrumento(id);
        if (!instrumento.isPresent()) {
            throw new InstrumentoNoEncontradoException("El instrumento con id " + id + " no existe");
        }
        return instrumento;
    }

    public void eliminarInstrumentoPorId(Long id) {
        Optional<InstrumentoFinancieroModel> instrumento = instrumentoRepository.eliminarInstrumento(id);
        if (!instrumento.isPresent()) {
            throw new InstrumentoNoEncontradoException("El instrumento con id " + id + " no existe");
        }
    }

    public void editarInstrumento(Long id, InstrumentoFinancieroModel nuevoInstrumento) {
        instrumentoRepository.editarInstrumento(id, nuevoInstrumento);
    }
}
