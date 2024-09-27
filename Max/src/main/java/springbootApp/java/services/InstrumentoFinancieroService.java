package springbootApp.java.services;


import org.springframework.beans.factory.annotation.Autowired;
import springbootApp.java.exceptions.InstrumentoNoEncontradoException;
import springbootApp.java.models.InstrumentoFinanciero;
import org.springframework.stereotype.Service;
import springbootApp.java.exceptions.InstrumentoDuplicadoException;
import springbootApp.java.models.InstrumentoFactory;
import springbootApp.java.models.Tipo;
import springbootApp.java.repositories.interfaces.IInstrumentoFinancieroRepository;
import springbootApp.java.utils.Validaciones;

import java.util.List;

@Service
public class InstrumentoFinancieroService {
    @Autowired
    private IInstrumentoFinancieroRepository instrumentoFinancieroRepository;


    public void registrarInstrumentoFinanciero(InstrumentoFinanciero instrumento) throws InstrumentoDuplicadoException {
        InstrumentoFinanciero instrumentoNuevo = instrumentoFinancieroRepository.findByNombre(instrumento.getNombre());
        if (instrumentoNuevo != null) {
            throw new InstrumentoDuplicadoException("Error. Accion existente");
        }
        if (Validaciones.validarDatosInstrumento(instrumento.getNombre(), instrumento.getPrecio(), instrumento.getTipo())) {
            this.instrumentoFinancieroRepository.save(
                    InstrumentoFactory.nuevoInstrumento(instrumento.getNombre(), instrumento.getPrecio(), instrumento.getTipo()));
        }
    }

    public List<InstrumentoFinanciero> consultarTodosLosInstrumentos() {
        return instrumentoFinancieroRepository.findAll();
    }

    public void eliminarInstrumento(String nombre) throws InstrumentoNoEncontradoException {
        InstrumentoFinanciero instrumento = instrumentoFinancieroRepository.findByNombre(nombre);
        if (instrumento == null) {
            throw new InstrumentoNoEncontradoException("Error. Instrumento no encontrado");
        }
        instrumentoFinancieroRepository.delete(instrumento);
    }

    public InstrumentoFinanciero buscarInstrumento(String nombre) throws InstrumentoNoEncontradoException {
        if (instrumentoFinancieroRepository.findByNombre(nombre) == null) {
            throw new InstrumentoNoEncontradoException("Error. Instrumento no encontrado");
        }
        return instrumentoFinancieroRepository.findByNombre(nombre);
    }

    public InstrumentoFinanciero actualizarInstrumento(String nombre, InstrumentoFinanciero instrumento) throws InstrumentoNoEncontradoException, InstrumentoDuplicadoException {
        InstrumentoFinanciero instrumentoEncontrado = instrumentoFinancieroRepository.findByNombre(nombre);
        if (instrumentoEncontrado == null) {
            throw new InstrumentoNoEncontradoException("Error. Instrumento no encontrado");
        }
        if (Validaciones.validarDatosInstrumento(instrumento.getNombre(), instrumento.getPrecio(), instrumento.getTipo())) {
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
}