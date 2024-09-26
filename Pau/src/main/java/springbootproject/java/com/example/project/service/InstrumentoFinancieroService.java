package springbootproject.java.com.example.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootproject.java.com.example.project.controller.dto.EditarInstrumentoDTO;
import springbootproject.java.com.example.project.controller.dto.InstrumentoFinancieroDTO;
import springbootproject.java.com.example.project.exceptions.InstrumentoDuplicadoException;
import springbootproject.java.com.example.project.exceptions.InstrumentoNoEncontradoException;
import springbootproject.java.com.example.project.exceptions.NoExisteEseTipoDeInstrumentoException;
import springbootproject.java.com.example.project.model.instrumentoFinanciero.InstrumentoFinanciero;
import springbootproject.java.com.example.project.model.instrumentoFinanciero.factoryInstrumentos.AccionFactory;
import springbootproject.java.com.example.project.model.instrumentoFinanciero.factoryInstrumentos.BonoFactory;
import springbootproject.java.com.example.project.repository.InstrumentosFinancierosRepository;

import java.util.List;

@Service
public class InstrumentoFinancieroService {
    private final InstrumentosFinancierosRepository instrumentosFinancierosRepository;

    @Autowired
    private InstrumentoFinancieroService(InstrumentosFinancierosRepository instrumentosFinancierosRepository) {
        this.instrumentosFinancierosRepository = instrumentosFinancierosRepository;
    }

    public List<InstrumentoFinanciero> consultarInstrumentosFinancieros() {
        return instrumentosFinancierosRepository.consultarInstrumentosFinancieros();
    }

    public InstrumentoFinanciero consultarPorUnInstrumentoFinanciero(String nombre) throws InstrumentoNoEncontradoException {
        InstrumentoFinanciero instrumentoFinanciero = this.instrumentosFinancierosRepository.consultarPorUnInstrumentoFinanciero(nombre);
        if (instrumentoFinanciero == null) {
            throw new InstrumentoNoEncontradoException("El instrumento con nombre " + nombre + " no fue encontrado.");
        }
        return instrumentoFinanciero;
    }

    public void eliminarInstrumentoFinanciero(String nombre) throws InstrumentoNoEncontradoException {
        this.instrumentosFinancierosRepository.eliminarInstrumentoFinanciero(nombre);
    }

    public InstrumentoFinanciero registrarInstrumentoFinanciero(InstrumentoFinancieroDTO instrumentoFinancieroDTO) throws InstrumentoDuplicadoException, NoExisteEseTipoDeInstrumentoException, InstrumentoNoEncontradoException {
        InstrumentoFinanciero instrumentoFinanciero = this.instrumentosFinancierosRepository.consultarPorUnInstrumentoFinanciero(instrumentoFinancieroDTO.getNombre());
        if(instrumentoFinanciero != null) {
            throw new InstrumentoDuplicadoException("No se puede registrar el instrumento debido a que este ya fue registrado en el sistema con anterioridad.");
        }

        switch (instrumentoFinancieroDTO.getTipoInstrumentoFinanciero()) {
            case BONO:
                BonoFactory bonoFactory = new BonoFactory();
                instrumentoFinanciero = bonoFactory.createInstrumentoFinanciero(instrumentoFinancieroDTO.getNombre(), instrumentoFinancieroDTO.getPrecio(), instrumentoFinancieroDTO.getFechaDeEmision());
                break;
            case ACCION:
                AccionFactory accionFactory = new AccionFactory();
                instrumentoFinanciero = accionFactory.createInstrumentoFinanciero(instrumentoFinancieroDTO.getNombre(), instrumentoFinancieroDTO.getPrecio(), instrumentoFinancieroDTO.getFechaDeEmision());
                break;
            default:
                throw new NoExisteEseTipoDeInstrumentoException("El tipo ingresado no corresponde a un tipo de instrumento conocido.");
        }
        return this.instrumentosFinancierosRepository.crearInstrumentoFinanciero(instrumentoFinanciero);
    }

    public InstrumentoFinanciero editarInstrumentoFinanciero(String nombreActual, EditarInstrumentoDTO editarInstrumentoDTO) throws InstrumentoNoEncontradoException {
        InstrumentoFinanciero instrumentoFinanciero = this.instrumentosFinancierosRepository.consultarPorUnInstrumentoFinanciero(nombreActual);
        if (instrumentoFinanciero == null) {
            throw new InstrumentoNoEncontradoException("El instrumento con nombre " + nombreActual + " no fue encontrado.");
        }
        return this.instrumentosFinancierosRepository.editarInstrumentoFinanciero(nombreActual, editarInstrumentoDTO.getNuevoNombre(), editarInstrumentoDTO.getNuevoPrecio(),editarInstrumentoDTO.getNuevaFechaDeEmision());
    }
}
