package springbootApp.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import  springbootApp.app.exceptions.InstrumentoNoEncontradoException;
import  springbootApp.app.models.InstrumentoFinanciero;
import org.springframework.stereotype.Service;
import  springbootApp.app.exceptions.InstrumentoDuplicadoException;
import  springbootApp.app.models.InstrumentoFactory;
import  springbootApp.app.repositories.interfaces.IInstrumentoFinancieroRepository;

import java.util.List;

import static springbootApp.app.utils.Validaciones.*;

@Service
public class InstrumentoFinancieroService {
    @Autowired
    private IInstrumentoFinancieroRepository instrumentoFinancieroRepository;


    public void registrarInstrumentoFinanciero(InstrumentoFinanciero instrumento) throws InstrumentoDuplicadoException {
        InstrumentoFinanciero instrumentoNuevo = instrumentoFinancieroRepository.findByNombre(instrumento.getNombre());
        if (instrumentoNuevo != null) {
            throw new InstrumentoDuplicadoException("Error. Accion existente");
        }
        if (validarDatosInstrumento(instrumento.getNombre(), instrumento.getPrecio(), instrumento.getTipo())) {
            this.instrumentoFinancieroRepository.save(
                    InstrumentoFactory.nuevoInstrumento(instrumento.getNombre(), instrumento.getPrecio(), instrumento.getTipo()));
        }
    }

    public List<InstrumentoFinanciero> consultarTodosLosInstrumentos() {
        return instrumentoFinancieroRepository.findAll();
    }

    public void eliminarInstrumento(Long id) throws InstrumentoNoEncontradoException {
        InstrumentoFinanciero instrumento = instrumentoFinancieroRepository.findById(id).orElse(null);
        if (instrumento == null) {
            throw new InstrumentoNoEncontradoException("Error. Instrumento no encontrado");
        }
        instrumentoFinancieroRepository.delete(instrumento);
    }

    public InstrumentoFinanciero buscarInstrumentoPorNombre(String nombre) throws InstrumentoNoEncontradoException {
        if (instrumentoFinancieroRepository.findByNombre(nombre) == null) {
            throw new InstrumentoNoEncontradoException("Error. Instrumento no encontrado");
        }
        return instrumentoFinancieroRepository.findByNombre(nombre);
    }

    public InstrumentoFinanciero actualizarInstrumento(Long id, InstrumentoFinanciero instrumento) throws InstrumentoNoEncontradoException, InstrumentoDuplicadoException {
        InstrumentoFinanciero instrumentoEncontrado = instrumentoFinancieroRepository.findById(id).orElse(null);
        if (instrumentoEncontrado == null) {
            throw new InstrumentoNoEncontradoException("Error. Instrumento no encontrado");
        }
        if (validarNombreDuplicado(instrumento.getNombre(), id, instrumentoFinancieroRepository)) {
            throw new InstrumentoDuplicadoException("Error. Instrumento con nombre ya existente");
        }
        if (validarDatosInstrumento(instrumento.getNombre(), instrumento.getPrecio(), instrumento.getTipo())) {
            instrumentoEncontrado.setNombre(instrumento.getNombre());
            instrumentoEncontrado.setTipo(instrumento.getTipo());
            if (instrumento.getPrecio() != instrumentoEncontrado.getPrecio()) {
                instrumentoEncontrado.setPrecio(instrumento.getPrecio());
                 ObserverService.notificarCambioDePrecio(instrumentoEncontrado);
            }
            instrumentoFinancieroRepository.save(instrumentoEncontrado);
        }
        return instrumentoEncontrado;
    }


    public InstrumentoFinanciero buscarInstrumentoPorID(Long id) throws InstrumentoNoEncontradoException {
        InstrumentoFinanciero instrumento = instrumentoFinancieroRepository.findById(id).orElse(null);
        if (instrumento == null) {
            throw new InstrumentoNoEncontradoException("Error. Instrumento no encontrado");
        }
        return instrumento;
    }
}