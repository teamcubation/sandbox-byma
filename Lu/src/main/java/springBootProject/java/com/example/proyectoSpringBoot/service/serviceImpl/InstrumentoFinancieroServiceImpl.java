package springBootProject.java.com.example.proyectoSpringBoot.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springBootProject.java.com.example.proyectoSpringBoot.dto.InstrumentoFinancieroDTO;
import springBootProject.java.com.example.proyectoSpringBoot.excepciones.InstrumentoDuplicadoException;
import springBootProject.java.com.example.proyectoSpringBoot.excepciones.InstrumentoNoEncontradoException;
import springBootProject.java.com.example.proyectoSpringBoot.model.InstrumentoFinanciero;
import springBootProject.java.com.example.proyectoSpringBoot.repository.InstrumentoFinancieroRepository;
import springBootProject.java.com.example.proyectoSpringBoot.service.InstrumentoFinancieroService;
import springBootProject.java.com.example.proyectoSpringBoot.service.factory.InstrumentoFinancieroFactory;


import java.util.List;
import java.util.Optional;

@Service
public class InstrumentoFinancieroServiceImpl implements InstrumentoFinancieroService {

    @Autowired
    InstrumentoFinancieroRepository instrumentoFinancieroRepository;

    public List<InstrumentoFinanciero> consultarTodos() {
        return this.instrumentoFinancieroRepository.consultarTodos();
    }

    public InstrumentoFinanciero consultar(String nombre) throws InstrumentoNoEncontradoException {
        Optional<InstrumentoFinanciero> instrumentoFinanciero = this.instrumentoFinancieroRepository.consultar(nombre);

        if (instrumentoFinanciero.isEmpty()) {
            throw new InstrumentoNoEncontradoException("El instrumento financiero con ese nombre no existe.");
        }

        return instrumentoFinanciero.get();
    }

    public InstrumentoFinanciero registrar(InstrumentoFinancieroDTO instrumentoFinancieroDTO) throws InstrumentoDuplicadoException {
        Optional<InstrumentoFinanciero> instrumentoFinanciero = this.instrumentoFinancieroRepository.buscarInstrumentoFinanciero(instrumentoFinancieroDTO.getNombre());

        if (instrumentoFinanciero.isPresent()) {
            throw new InstrumentoDuplicadoException("El instrumento financiero a registrar ya existe, no se permiten instrumentos duplicados.");
        }

        InstrumentoFinanciero instrumentoFinancieroNuevo = InstrumentoFinancieroFactory.crearInstrumentoFinanciero(instrumentoFinancieroDTO.getTipo());
        instrumentoFinancieroNuevo.setPrecio(instrumentoFinancieroDTO.getPrecio());
        instrumentoFinancieroNuevo.setNombre(instrumentoFinancieroDTO.getNombre());

        this.instrumentoFinancieroRepository.registrar(instrumentoFinancieroNuevo);

        return instrumentoFinancieroNuevo;
    }


    public InstrumentoFinanciero eliminar(String nombre) throws InstrumentoNoEncontradoException {
        Optional<InstrumentoFinanciero> instrumentoFinanciero = this.instrumentoFinancieroRepository.buscarInstrumentoFinanciero(nombre);

        if (instrumentoFinanciero.isEmpty()) {
            throw new InstrumentoNoEncontradoException("El instrumento financiero a eliminar no existe.");
        }

        this.instrumentoFinancieroRepository.eliminar(instrumentoFinanciero.get());

        return instrumentoFinanciero.get();
    }

    public InstrumentoFinanciero editar(String instrumentoAEditar, InstrumentoFinancieroDTO instrumentoFinancieroDTO) throws InstrumentoNoEncontradoException {
        Optional<InstrumentoFinanciero> instrumentoFinanciero = this.instrumentoFinancieroRepository.buscarInstrumentoFinanciero(instrumentoAEditar);

        if (instrumentoFinanciero.isEmpty()) {
            throw new InstrumentoNoEncontradoException("El instrumento financiero a editar no existe.");
        }

        InstrumentoFinanciero instrumentoFinancieroAEditar = instrumentoFinanciero.get();
        instrumentoFinancieroAEditar.setNombre(instrumentoFinancieroDTO.getNombre());
        instrumentoFinancieroAEditar.setPrecio(instrumentoFinancieroDTO.getPrecio());

        return instrumentoFinancieroAEditar;
    }
}
