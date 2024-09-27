package springbootApp.java.services;


import org.springframework.beans.factory.annotation.Autowired;
import springbootApp.java.exceptions.InstrumentoNoEncontradoException;
import springbootApp.java.models.InstrumentoFinanciero;
import org.springframework.stereotype.Service;
import springbootApp.java.exceptions.InstrumentoDuplicadoException;
import springbootApp.java.models.InstrumentoFactory;
import springbootApp.java.models.Tipo;
import springbootApp.java.repositories.InstrumentoFinancieroRepository;
import springbootApp.java.utils.Validaciones;

import java.util.List;

@Service
public class InstrumentoFinancieroService {
    @Autowired
    private InstrumentoFinancieroRepository instrumentoFinancieroRepository;


    public void registrarInstrumentoFinanciero(InstrumentoFinanciero instrumento) throws InstrumentoDuplicadoException {
        InstrumentoFinanciero instrumentoNuevo = instrumentoFinancieroRepository.buscarInstrumento(instrumento.getNombre());
        if (instrumentoNuevo != null) {
            throw new InstrumentoDuplicadoException("Error. Accion existente");
        }
        if (validarDatos(instrumento.getNombre(), instrumento.getPrecio(), instrumento.getTipo())) {
            this.instrumentoFinancieroRepository.registrarInstrumento(
                    InstrumentoFactory.nuevoInstrumento(instrumento.getNombre(), instrumento.getPrecio(), instrumento.getTipo()));
        }
    }

    public List<InstrumentoFinanciero> consultarTodosLosInstrumentos() {
        return instrumentoFinancieroRepository.consultarTodosLosInstrumentos();
    }

    public void eliminarInstrumento(String nombre) throws InstrumentoNoEncontradoException {
        InstrumentoFinanciero instrumento = instrumentoFinancieroRepository.buscarInstrumento(nombre);
        if (instrumento == null) {
            throw new InstrumentoNoEncontradoException("Error. Instrumento no encontrado");
        }
        instrumentoFinancieroRepository.eliminarInstrumento(instrumento);
    }

    public InstrumentoFinanciero buscarInstrumento(String nombre) throws InstrumentoNoEncontradoException {
        if (instrumentoFinancieroRepository.buscarInstrumento(nombre) == null) {
            throw new InstrumentoNoEncontradoException("Error. Instrumento no encontrado");
        }
        return instrumentoFinancieroRepository.buscarInstrumento(nombre);
    }

    public InstrumentoFinanciero actualizarInstrumento(String nombre, InstrumentoFinanciero instrumento) throws InstrumentoNoEncontradoException, InstrumentoDuplicadoException {
        InstrumentoFinanciero instrumentoEncontrado = this.buscarInstrumento(nombre);
        if (instrumentoEncontrado == null) {
            throw new InstrumentoNoEncontradoException("Error. Instrumento no encontrado");
        }
        if (validarDatos(instrumento.getNombre(), instrumento.getPrecio(), instrumento.getTipo())) {
            instrumentoEncontrado.setNombre(instrumento.getNombre());
            instrumentoEncontrado.setTipo(instrumento.getTipo());
            if (instrumento.getPrecio() != instrumentoEncontrado.getPrecio()) {
                instrumentoEncontrado.setPrecio(instrumento.getPrecio());
                ObserverService.notificarCambioDePrecio(instrumentoEncontrado);
            }
        }
        return instrumento;
    }

    private boolean validarDatos(String nombre, double precio, Tipo tipo) {
        return Validaciones.validarNombre(nombre) && Validaciones.validarPrecio(precio) && Validaciones.validarTipo(tipo);
    }

}