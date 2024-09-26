package springbootApp.java.services;


import org.springframework.beans.factory.annotation.Autowired;
import springbootApp.java.exceptions.InstrumentoNoEncontradoException;
import springbootApp.java.DTOs.InstrumentoDTO;
import springbootApp.java.models.InstrumentoFinanciero;
import org.springframework.stereotype.Service;
import springbootApp.java.exceptions.InstrumentoDuplicadoException;
import springbootApp.java.models.InstrumentoFactory;
import springbootApp.java.models.Inversor;
import springbootApp.java.models.Tipo;
import springbootApp.java.repositories.InstrumentoFinancieroRepository;
import springbootApp.java.utils.Validaciones;

import java.util.List;

@Service
public class InstrumentoFinancieroService {
    @Autowired
    private InstrumentoFinancieroRepository instrumentoFinancieroRepository;


    public void registrarInstrumentoFinanciero(String nombre, double precio, Tipo tipo) throws InstrumentoDuplicadoException {
        InstrumentoFinanciero instrumento = instrumentoFinancieroRepository.buscarInstrumento(nombre);
        if (instrumento != null) {
            throw new InstrumentoDuplicadoException("Error. Accion existente");
        }
        if (validarDatos(nombre, precio, tipo)) {
            this.instrumentoFinancieroRepository.registrarInstrumento(
                    InstrumentoFactory.nuevoInstrumento(nombre, precio, tipo));
        }
    }

    public List<InstrumentoFinanciero> consultarTodosLosInstrumentos() {
        for (InstrumentoFinanciero instrumento : instrumentoFinancieroRepository.consultarTodosLosInstrumentos()) {
            System.out.println(instrumento);
        }
        return instrumentoFinancieroRepository.consultarTodosLosInstrumentos();
    }

    public void eliminarInstrumento(String nombre) throws InstrumentoNoEncontradoException {
        InstrumentoFinanciero instrumento = instrumentoFinancieroRepository.buscarInstrumento(nombre);
        if (instrumento == null) {
            throw new InstrumentoNoEncontradoException("Error. Instrumento no encontrado");
        }
        instrumentoFinancieroRepository.eliminarInstrumento(instrumento);
    }

    public InstrumentoFinanciero buscarInstrumento(String nombre) {
        return instrumentoFinancieroRepository.buscarInstrumento(nombre);
    }

    public InstrumentoDTO actualizarInstrumento(String nombre, InstrumentoDTO instrumento) throws InstrumentoNoEncontradoException, InstrumentoDuplicadoException {
        InstrumentoFinanciero instrumentoEncontrado = this.buscarInstrumento(nombre);
        if (instrumentoEncontrado == null) {
            throw new InstrumentoNoEncontradoException("Error. Instrumento no encontrado");
        }
        validarDatos(instrumento.getNombre(), instrumento.getPrecio(), instrumento.getTipo());
        instrumentoEncontrado.setNombre(instrumento.getNombre());
        instrumentoEncontrado.setTipo(instrumento.getTipo());
        if (instrumento.getPrecio() != instrumentoEncontrado.getPrecio()) {
            instrumentoEncontrado.notificar();
            instrumentoEncontrado.setPrecio(instrumento.getPrecio());
        }
        return instrumento;
    }

    private boolean validarDatos(String nombre, double precio, Tipo tipo) {
        return Validaciones.validarNombre(nombre) && Validaciones.validarPrecio(precio) && Validaciones.validarTipo(tipo);
    }

}